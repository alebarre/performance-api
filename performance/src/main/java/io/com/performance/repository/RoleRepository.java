package io.com.performance.repository;

import io.com.performance.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleRepository<T extends Role> {

    /* Basic CRUD operations*/
    T create(T data);
    Collection<T> list();
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /* More complex CRUD operations*/
    void addRoleToUser (Long userId, String roleName);
    Role getRoleByUserId(Long userId);
    Role getRoleByUserEmail(String email);
    void updateUserRole(Long userId, String roleName);
}