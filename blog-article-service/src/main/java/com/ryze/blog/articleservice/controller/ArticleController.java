package com.ryze.blog.articleservice.controller;

import com.ryze.blog.articleservice.dto.ArticleCreateRequest;
import com.ryze.blog.articleservice.dto.ArticleDetailVO;
import com.ryze.blog.articleservice.dto.ArticleSimpleVO;
import com.ryze.blog.articleservice.service.ArticleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public Long createArticle(@RequestBody ArticleCreateRequest request) {
        return articleService.createArticle(request);
    }

    @GetMapping("/public/{id}")
    public ArticleDetailVO getPublicArticle(@PathVariable Long id) {
        return articleService.getPublicArticleById(id);
    }

    @GetMapping("/public/list")
    public List<ArticleSimpleVO> listPublicArticles() {
        return articleService.listPublicArticles();
    }

    @GetMapping("/private/{id}")
    public ArticleDetailVO getPrivateArticle(@PathVariable Long id) {
        return articleService.getPrivateArticleById(id);
    }
}
