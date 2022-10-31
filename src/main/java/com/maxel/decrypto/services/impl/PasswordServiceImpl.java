package com.maxel.decrypto.services.impl;

import com.maxel.decrypto.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Boolean isValidPassword(String objectPass, String receivedPass) {
        return bCryptPasswordEncoder.matches(receivedPass, objectPass);
    }

    @Override
    public String encodePass(String pass) {
        return bCryptPasswordEncoder.encode(pass);
    }
}
