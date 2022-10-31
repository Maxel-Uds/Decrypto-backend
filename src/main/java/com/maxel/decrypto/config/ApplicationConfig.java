package com.maxel.decrypto.config;

import com.maxel.decrypto.services.EncryptorService;
import com.maxel.decrypto.services.MessageService;
import com.maxel.decrypto.services.impl.CodeServiceImpl;
import com.maxel.decrypto.services.impl.DecodeServiceImpl;
import com.maxel.decrypto.services.impl.MessageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean("decodeService")
    public EncryptorService decodeService() {
        return new DecodeServiceImpl();
    }

    @Bean("codeService")
    public EncryptorService codeService() {
        return new CodeServiceImpl();
    }

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl();
    }
}
