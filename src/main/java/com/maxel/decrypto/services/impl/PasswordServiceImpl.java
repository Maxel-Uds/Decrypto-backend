package com.maxel.decrypto.services.impl;

import com.maxel.decrypto.services.PasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Boolean isValidPassword(String objectPass, String receivedPass) {
        log.info("===== Verificando se a senha é válida ======");
        return bCryptPasswordEncoder.matches(receivedPass, objectPass);
    }

    @Override
    public String encodePass(String pass) {
        log.info("===== Codificando senha ======");
        return bCryptPasswordEncoder.encode(pass);
    }
}
