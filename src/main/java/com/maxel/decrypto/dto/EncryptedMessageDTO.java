package com.maxel.decrypto.dto;

import com.maxel.decrypto.domain.MessageRequest;

import java.io.Serializable;

public class EncryptedMessageDTO implements Serializable {

    private String message;

    public EncryptedMessageDTO() {}

    public EncryptedMessageDTO(MessageRequest message) {
        this.message = message.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
