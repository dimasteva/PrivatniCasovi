package com.jahaci.edukacija.repository;

import com.jahaci.edukacija.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    Optional<User> tryLogin(@Param("username") String username, @Param("password") String password);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username")
    boolean usernameExists(@Param("username") String username);

}
