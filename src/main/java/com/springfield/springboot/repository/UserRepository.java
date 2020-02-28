package com.springfield.springboot.repository;

import com.springfield.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select s.password from User s where s.username = ?1")
    String lookupStudentPassword(String username);

    @Query("select u.id from User u where u.username = ?1")
    long findStudentIDByUsername(String username);
}
