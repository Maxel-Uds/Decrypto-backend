package com.maxel.decrypto.services.impl;

import com.maxel.decrypto.domain.Message;
import com.maxel.decrypto.repositories.MessageRepository;
import com.maxel.decrypto.services.MessageService;
import com.maxel.decrypto.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository repository;

    @Override
    public Message findMessageById(String id) {
        log.info("===== Buscando mensagem com o id [{}] ======", id);
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Nenhuma mensagem foi encontrada com o ID: " + id));
    }

    @Override
    public Message saveMessage(Message message) {
        log.info("===== Salvando mensagem [{}] ======", message.getId());
        return repository.save(message);
    }

    @Override
    public void deleteMessageById(String id) {
        log.info("===== Excluíndo mensagem com o id [{}] ======", id);
        repository.deleteById(id);
    }
}
