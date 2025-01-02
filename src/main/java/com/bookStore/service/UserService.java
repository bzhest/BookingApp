package com.bookStore.service;

import com.bookStore.dto.UserDto;
import com.bookStore.entity.Role;
import com.bookStore.entity.User;
import com.bookStore.repository.RoleRepository;
import com.bookStore.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @NonNull
    public User createUser(UserDto userDto) {
        Role role = getRoleById(userDto.getRoleId());
        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(role);

        return userRepository.save(user);
    }

    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(Integer userId, UserDto newUser) {
        Role role = getUserById(newUser.getId()).getRole();
        User user = userRepository.findById(userId).orElse(null);
        if (nonNull(user)) {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setAddress(newUser.getAddress());
            user.setPhone(newUser.getPhone());
            user.setLogin(newUser.getLogin());
            user.setPassword(newUser.getPassword());
            user.setRole(role);
        }
    }


}
