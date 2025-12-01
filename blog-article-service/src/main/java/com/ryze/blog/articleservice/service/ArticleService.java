package com.ryze.blog.articleservice.service;

import com.ryze.blog.articleservice.dto.ArticleCreateRequest;
import com.ryze.blog.articleservice.dto.ArticleDetailVO;
import com.ryze.blog.articleservice.dto.ArticleSimpleVO;
import java.util.List;

public interface ArticleService {

    Long createArticle(ArticleCreateRequest request);

    ArticleDetailVO getPublicArticleById(Long id);

    List<ArticleSimpleVO> listPublicArticles();

    ArticleDetailVO getPrivateArticleById(Long id);
}
