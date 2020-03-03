package com.springfield.springboot.repository;

import com.springfield.springboot.model.User;
import com.springfield.springboot.model.NationalityCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.id from User u where u.username = ?1")
    Long findStudentIDByUsername(String username);

    @Query("select count(u)  " +
            "from User as u " +
            "WHERE u.sex = ?1")
    public Long countUsersBySex(char sex);

    @Query("select new com.springfield.springboot.model.NationalityCount(u.nationality, count(u.nationality)) from User u GROUP BY u.nationality")
    List<NationalityCount> findNationalityCounts();
}