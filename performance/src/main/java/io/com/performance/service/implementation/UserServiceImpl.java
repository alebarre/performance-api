package io.com.performance.service.implementation;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.User;
import io.com.performance.dtomapper.UserDTOMapper;
import io.com.performance.repository.UserRepository;
import io.com.performance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}
