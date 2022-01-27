package com.maxel.decrypto.dto;

import java.io.Serializable;

public class EncryptedMessageDTO implements Serializable {

    private String message;

    public EncryptedMessageDTO() {}

    public EncryptedMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
