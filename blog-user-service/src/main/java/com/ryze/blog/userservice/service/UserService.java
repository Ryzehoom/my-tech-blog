package com.ryze.blog.userservice.service;

import com.ryze.blog.userservice.domain.User;
import com.ryze.blog.userservice.dto.RegisterRequest;
import com.ryze.blog.userservice.dto.UserSimpleVO;

public interface UserService {

    UserSimpleVO register(RegisterRequest request);

    User findByUsername(String username);
}
