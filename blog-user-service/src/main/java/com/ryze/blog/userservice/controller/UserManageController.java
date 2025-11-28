package com.ryze.blog.userservice.controller;

import com.ryze.blog.userservice.domain.User;
import com.ryze.blog.userservice.dto.RegisterRequest;
import com.ryze.blog.userservice.dto.UserSimpleVO;
import com.ryze.blog.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserManageController {

    private final UserService userService;

    @PostMapping("/register-simple")
    public UserSimpleVO registerSimple(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/by-name")
    public UserSimpleVO findByUsername(@RequestParam String username) {
        User user = userService.findByUsername(username);
        return toSimpleVO(user);
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
