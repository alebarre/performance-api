package io.com.performance.service;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.User;

public interface UserService {

    UserDTO createUser(User user);

}
