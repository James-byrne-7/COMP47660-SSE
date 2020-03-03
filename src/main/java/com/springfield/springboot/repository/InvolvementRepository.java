package com.springfield.springboot.repository;

import com.springfield.springboot.exception.UserNotFoundException;
import com.springfield.springboot.model.Involvement;
import com.springfield.springboot.model.InvolvementID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvolvementRepository extends JpaRepository<Involvement, InvolvementID> {

    @Query("select i.module_id from Involvement i where i.user_id = ?1")
    List<Long> findModuleByUserId(Long user_id) throws UserNotFoundException;

    @Query("select count(u)  " +
            "from Involvement i, User as u " +
            "WHERE i.module_id = ?1 " +
            "AND i.user_id = u.id " +
            "AND u.sex = ?2")
    public Long countUsersInvolvedBySex(Long moduleID, char sex);

    @Query("select count(i) from Involvement i where i.module_id = ?1")
    public Long studentsEnrolled(Long a);

}



