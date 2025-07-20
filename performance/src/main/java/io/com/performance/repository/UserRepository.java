package io.com.performance.repository;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.User;
import io.com.performance.form.UpdateForm;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Repository
public interface UserRepository<T extends User> {

    /* Basic CRUD operations*/

    T create (T data);
    Collection<T> list (int page, int pageSize);
    T get (Long id);
    T update (T data);
    Boolean delete (Long id);

    /* More complex CRUD operations*/
    User getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);

    User verifyCode(String email, String code);

    void resetPassword(String email);

    T verifyPasswordKey(String key);

    void renewPassword(String key, String password, String confirmPassword);

    T verifyAccount(String key);

    T updateUserDetails(UpdateForm user);

    void updatePassword(Long id, String currentPassword, String newPassword, String confirmNewPassword);

    void updateUserAccount(Long userId, Boolean enabled, Boolean notLocked);

    T toggleMfa(String email);

    void updateImage(UserDTO user, MultipartFile image);
}
