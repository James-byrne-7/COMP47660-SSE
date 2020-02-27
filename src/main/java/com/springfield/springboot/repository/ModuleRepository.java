package com.springfield.springboot.repository;

import com.springfield.springboot.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.springfield.springboot.model.Student;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
