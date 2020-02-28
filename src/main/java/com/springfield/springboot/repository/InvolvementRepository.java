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

}



