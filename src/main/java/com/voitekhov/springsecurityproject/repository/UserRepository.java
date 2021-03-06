package com.voitekhov.springsecurityproject.repository;

import com.voitekhov.springsecurityproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u where u.username =:username")
    Optional<User> findByUsername(@Param("username") String username);

}
