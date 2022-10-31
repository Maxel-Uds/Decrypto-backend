package com.maxel.decrypto.services.impl;

import com.maxel.decrypto.domain.Message;
import com.maxel.decrypto.repositories.MessageRepository;
import com.maxel.decrypto.services.MessageService;
import com.maxel.decrypto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository repository;

    @Override
    public Message findMessageById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> { throw new ObjectNotFoundException("Nenhuma mensagem foi encontrada com o ID: " + id); });
    }

    @Override
    public Message saveMessage(Message message) {
        return repository.save(message);
    }

    @Override
    public void deleteMessageById(String id) {
        repository.deleteById(id);
    }
}
