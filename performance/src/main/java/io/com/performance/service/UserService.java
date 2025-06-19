package io.com.performance.service;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.User;

public interface UserService {

    UserDTO createUser(User user);

    UserDTO getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);

    User getUser(String email);

    UserDTO getUserById(Long userId);

}
