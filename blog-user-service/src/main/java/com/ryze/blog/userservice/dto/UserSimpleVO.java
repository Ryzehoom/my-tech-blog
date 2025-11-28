package com.ryze.blog.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleVO {
    private Long id;
    private String username;
    private String nickname;
    private String role;
    private Integer status;
}
