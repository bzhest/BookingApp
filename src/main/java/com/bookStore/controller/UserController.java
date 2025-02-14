package com.bookStore.controller;

import com.bookStore.dto.UserDto;
import com.bookStore.entity.User;
import com.bookStore.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String createUser(@RequestBody UserDto userDto) {
            userService.createUser(userDto);
            return "User with email " + userDto.getEmail() + " was created";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'CUSTOMER')")
    public void updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'CUSTOMER')")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/role")
    public ResponseEntity<String> getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return ResponseEntity.ok("{\"user_role\":\"" + authorities.toString() + "\"}");

        //return authorities.toString();
    }
}
