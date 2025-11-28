package com.ryze.blog.articleservice.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_article")
public class Article {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String summary;

    @TableField("content_md")
    private String contentMd;

    @TableField("content_html")
    private String contentHtml;

    @TableField("category_id")
    private Long categoryId;

    @TableField("is_private")
    private Integer isPrivate;

    @TableField("author_username")
    private String authorUsername;

    @TableField("view_count")
    private Long viewCount;

    @TableField("like_count")
    private Long likeCount;

    private Integer status;

    @TableField("publish_time")
    private LocalDateTime publishTime;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
