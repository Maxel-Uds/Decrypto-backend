package com.maxel.decrypto.dto;

public class EncryptedMessageDTO {

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
