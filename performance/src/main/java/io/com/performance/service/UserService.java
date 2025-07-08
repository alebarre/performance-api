package io.com.performance.service;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.User;

public interface UserService {

    UserDTO createUser(User user);

    UserDTO getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);

    UserDTO getUserById(Long userId);

    UserDTO verifyCode(String email, String code);

    void resetPassword(String email);

    UserDTO verifyPasswordKey(String email);

    void renewPassword(String key, String password, String confirmPassword);

    UserDTO verifyAccount(String key);
}
