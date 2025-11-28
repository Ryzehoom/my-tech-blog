package com.ryze.blog.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ryze.blog.userservice.domain.User;
import com.ryze.blog.userservice.dto.RegisterRequest;
import com.ryze.blog.userservice.dto.UserSimpleVO;
import com.ryze.blog.userservice.mapper.UserMapper;
import com.ryze.blog.userservice.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserSimpleVO register(RegisterRequest request) {
        User existed = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, request.getUsername()));
        if (existed != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        long count = userMapper.selectCount(null);
        String role = count == 0 ? "ADMIN" : "USER";
        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .role(role)
                .status(1)
                .createdAt(now)
                .updatedAt(now)
                .build();

        userMapper.insert(user);
        return toSimpleVO(user);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
    }

    private UserSimpleVO toSimpleVO(User user) {
        if (user == null) {
            return null;
        }
        return UserSimpleVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}
