package com.maxel.decrypto.dto;

import com.maxel.decrypto.domain.MessageRequest;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    private String message;

    public MessageDTO(String message) { this.message = message; }

    public MessageDTO(MessageRequest request) {
        this.message = request.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
