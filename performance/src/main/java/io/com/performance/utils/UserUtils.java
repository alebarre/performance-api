package io.com.performance.utils;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.UserPrincipal;
import org.springframework.security.core.Authentication;

public class UserUtils {

    public static UserDTO getAuthenticatedUser (Authentication authentication) {
        return (UserDTO) authentication.getPrincipal();
    }

    public static UserDTO getLoggedInUser(Authentication authentication) {
        return ((UserPrincipal) authentication.getPrincipal()).getUser();
    }

}
