package com.springfield.springboot.repository;

import com.springfield.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.id from User u where u.username = ?1")
    String findStudentIDByUsername(String username);
}
