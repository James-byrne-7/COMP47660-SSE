package com.springfield.springboot.repository;

import com.springfield.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Deprecated
    @Query("select u.id from User u where u.username = ?1")
    Long findStudentIDByUsername(String username);

    @Query("select count(u)  " +
            "from User as u " +
            "WHERE u.sex = ?1")
    public Long countUsersBySex(char sex);
}