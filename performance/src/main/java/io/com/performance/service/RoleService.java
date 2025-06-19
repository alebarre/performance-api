package io.com.performance.service;

import io.com.performance.domain.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getRoleByUserId(Long id);
}
