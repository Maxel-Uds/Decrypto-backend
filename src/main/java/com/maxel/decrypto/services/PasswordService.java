package com.maxel.decrypto.services;

public interface PasswordService {

    Boolean isValidPassword(String objectPass, String receivedPass);
    String encodePass(String pass);
}
