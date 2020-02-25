package com.springfield.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springfield.springboot.model.Student;
import com.springfield.springboot.model.StaffMember;


@Repository
public interface StaffRepository extends JpaRepository<StaffMember, Long> {
    @Query("select s.password from StaffMember s where s.username = ?1")
    String lookupStaffPassword(String username);
}
