package io.com.performance.repository;

import io.com.performance.domain.User;
import org.springframework.stereotype.Repository;

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

}
