package com.springfield.springboot.repository;

import com.springfield.springboot.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
//    @Query("select a.id_author from Authorship a where a.id_book = ?1")
//    List<Long> findUsersModules(Long book_id);
}
