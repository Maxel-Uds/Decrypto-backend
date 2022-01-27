package com.maxel.decrypto.dto;

import java.io.Serializable;

public class DencryptedMessageDTO implements Serializable {

    private String message;

    public DencryptedMessageDTO() {}

    public DencryptedMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
