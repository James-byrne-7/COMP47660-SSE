package com.springfield.springboot.repository;

import com.springfield.springboot.model.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetTokenRepository extends JpaRepository<ResetToken, String> {
    ResetToken findByValue(String token);
}