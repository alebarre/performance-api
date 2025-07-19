package io.com.performance.service;

import io.com.performance.domain.Role;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface RoleService {
    Role getRoleByUserId(Long id);
    Collection<Role> getRoles();
}
