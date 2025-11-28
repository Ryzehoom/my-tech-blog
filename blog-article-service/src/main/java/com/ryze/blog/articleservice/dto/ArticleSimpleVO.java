package com.ryze.blog.articleservice.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSimpleVO {
    private Long id;
    private String title;
    private String summary;
    private Long categoryId;
    private Integer isPrivate;
    private String authorUsername;
    private LocalDateTime publishTime;
}
