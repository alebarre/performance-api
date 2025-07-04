package io.com.performance.service.implementation;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.Role;
import io.com.performance.domain.User;
import io.com.performance.repository.RoleRepository;
import io.com.performance.repository.UserRepository;
import io.com.performance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static io.com.performance.dtomapper.UserDTOMapper.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;
    private final RoleRepository<Role> roleRoleRepository;

    @Override
    public UserDTO createUser(User user) {
        return mapToUserDTO(userRepository.create(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return mapToUserDTO(userRepository.getUserByEmail(email));
    }

    @Override
    public void sendVerificationCode(UserDTO user) {
        userRepository.sendVerificationCode(user);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return mapToUserDTO(userRepository.get(userId));
    }

    @Override
    public UserDTO verifyCode(String email, String code) {
        return mapToUserDTO(userRepository.verifyCode(email, code));
    }

    @Override
    public void resetPassword(String email) {
        userRepository.resetPassword(email);
    }

    @Override
    public UserDTO verifyPasswordKey(String key) {
        return mapToUserDTO(userRepository.verifyPasswordKey(key));
    }

    @Override
    public void renewPassword(String key, String password, String confirmPassword) {
        userRepository.renewPassword(key, password, confirmPassword);
    }

    private UserDTO mapToUserDTO(User user) {
        return fromUser(user, roleRoleRepository.getRoleByUserId(user.getId()));
    }


}
