package com.maxel.decrypto.services;

import org.springframework.stereotype.Service;

@Service
public interface PasswordService {

    Boolean isValidPassword(String objectPass, String receivedPass);
    String encodePass(String pass);
}
