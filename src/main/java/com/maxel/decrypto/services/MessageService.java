package com.maxel.decrypto.services;

import com.maxel.decrypto.domain.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {

    Message findMessageById(String id);
    Message saveMessage(Message message);
    void deleteMessageById(String id);
}
