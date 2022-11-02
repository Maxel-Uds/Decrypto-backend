package com.maxel.decrypto.services;

import com.maxel.decrypto.domain.Message;

public interface MessageService {

    Message findMessageById(String id);
    Message saveMessage(Message message);
    void deleteMessageById(String id);
}
