package com.maxel.decrypto.config;

import com.maxel.decrypto.services.EncryptorService;
import com.maxel.decrypto.services.MessageService;
import com.maxel.decrypto.services.PasswordService;
import com.maxel.decrypto.services.impl.CodeServiceImpl;
import com.maxel.decrypto.services.impl.DecodeServiceImpl;
import com.maxel.decrypto.services.impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final PasswordService passwordService;

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl();
    }

    @Bean("decodeService")
    public EncryptorService decodeService() {
        return new DecodeServiceImpl(passwordService, messageService());
    }

    @Bean("codeService")
    public EncryptorService codeService() {
        return new CodeServiceImpl(passwordService, messageService());
    }
}
