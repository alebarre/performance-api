package io.com.performance.dtomapper;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.Role;
import io.com.performance.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

        public static UserDTO fromUser(User user) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }

        public static UserDTO fromUser(User user, Role role) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setRoleName(role.getName());
            userDTO.setPermissions(role.getPermission());
            return userDTO;
        }

        public static User toUser(UserDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        }
}
