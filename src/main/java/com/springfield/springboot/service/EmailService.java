package com.springfield.springboot.service;

import com.springfield.springboot.model.ResetToken;

public interface EmailService {
    void sendResetToken(String email, ResetToken resetToken);
}
