package com.test.xpress.repository;

import com.test.xpress.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // obtener usuario por userName
    Optional<User> findByUserNameAndPasswordHash(String username, String passwordHash);
    Optional<User> findByUserName(String username);
}
