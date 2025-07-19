package io.com.performance.service;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.User;
import io.com.performance.form.UpdateForm;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

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

    UserDTO updateUserDetails(UpdateForm user);

    void updatePassword(Long id, String currentPassword, String newPassword, String confirmNewPassword);

    void updateUserRole(Long userId, String roleName);
}
