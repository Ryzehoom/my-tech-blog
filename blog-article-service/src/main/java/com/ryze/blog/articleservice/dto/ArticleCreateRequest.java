package com.ryze.blog.articleservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class ArticleCreateRequest {
    private String title;
    private String summary;
    private String contentMd;
    private Long categoryId;
    private List<Long> tagIds;
    private String coverImage;
    private Boolean privateFlag;
}
