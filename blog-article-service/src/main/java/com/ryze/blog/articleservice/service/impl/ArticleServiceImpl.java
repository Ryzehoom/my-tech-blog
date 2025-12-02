package com.ryze.blog.articleservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ryze.blog.articleservice.domain.Article;
import com.ryze.blog.articleservice.domain.ArticleTag;
import com.ryze.blog.articleservice.dto.ArticleCreateRequest;
import com.ryze.blog.articleservice.dto.ArticleDetailVO;
import com.ryze.blog.articleservice.dto.ArticleSimpleVO;
import com.ryze.blog.articleservice.mapper.ArticleMapper;
import com.ryze.blog.articleservice.mapper.ArticleTagMapper;
import com.ryze.blog.articleservice.service.ArticleService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleTagMapper articleTagMapper;

    @Override
    public Long createArticle(ArticleCreateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        String username = authentication.getName();
        LocalDateTime now = LocalDateTime.now();
        Integer isPrivate = Boolean.TRUE.equals(request.getPrivateFlag()) ? 1 : 0;

        Article article = Article.builder()
                .title(request.getTitle())
                .summary(request.getSummary())
                .contentMd(request.getContentMd())
                .contentHtml(null)
                .categoryId(request.getCategoryId())
                .isPrivate(isPrivate)
                .authorUsername(username)
                .viewCount(0L)
                .likeCount(0L)
                .wowCount(0L)
                .coverImage(request.getCoverImage())
                .status(1)
                .publishTime(now)
                .createdAt(now)
                .updatedAt(now)
                .build();

        articleMapper.insert(article);

        if (request.getTagIds() != null && !request.getTagIds().isEmpty()) {
            for (Long tagId : request.getTagIds()) {
                if (tagId == null) {
                    continue;
                }
                ArticleTag articleTag = ArticleTag.builder()
                        .articleId(article.getId())
                        .tagId(tagId)
                        .createdAt(now)
                        .build();
                articleTagMapper.insert(articleTag);
            }
        }

        return article.getId();
    }

    @Override
    public ArticleDetailVO getPublicArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        }
        return toDetailVO(article);
    }

    @Override
    public List<ArticleSimpleVO> listPublicArticles() {
        List<Article> articles = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                .eq(Article::getIsPrivate, 0)
                .eq(Article::getStatus, 1)
                .orderByDesc(Article::getPublishTime));

        List<ArticleSimpleVO> result = new ArrayList<>();
        for (Article article : articles) {
            result.add(toSimpleVO(article));
        }
        return result;
    }

    @Override
    public ArticleDetailVO getPrivateArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
        }
        if (!Objects.equals(article.getIsPrivate(), 1)) {
            return toDetailVO(article);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        String username = authentication.getName();
        if (!Objects.equals(username, article.getAuthorUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
        return toDetailVO(article);
    }

    private ArticleDetailVO toDetailVO(Article article) {
        return ArticleDetailVO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .summary(article.getSummary())
                .contentMd(article.getContentMd())
                .contentHtml(article.getContentHtml())
                .categoryId(article.getCategoryId())
                .isPrivate(article.getIsPrivate())
                .authorUsername(article.getAuthorUsername())
                .viewCount(article.getViewCount())
                .likeCount(article.getLikeCount())
                .wowCount(article.getWowCount())
                .coverImage(article.getCoverImage())
                .status(article.getStatus())
                .publishTime(article.getPublishTime())
                .build();
    }

    private ArticleSimpleVO toSimpleVO(Article article) {
        return ArticleSimpleVO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .summary(article.getSummary())
                .categoryId(article.getCategoryId())
                .isPrivate(article.getIsPrivate())
                .authorUsername(article.getAuthorUsername())
                .coverImage(article.getCoverImage())
                .publishTime(article.getPublishTime())
                .build();
    }
}
