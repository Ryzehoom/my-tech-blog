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
public class ArticleDetailVO {
    private Long id;
    private String title;
    private String summary;
    private String contentMd;
    private String contentHtml;
    private Long categoryId;
    private Integer isPrivate;
    private String authorUsername;
    private Long viewCount;
    private Long likeCount;
    private Long wowCount;
    private String coverImage;
    private Integer status;
    private LocalDateTime publishTime;
}
