package com.maxel.decrypto.resources.request;

import javax.validation.constraints.NotEmpty;

public class DecodeMessageRequest {

    @NotEmpty(message = "Preenchimento obrigatótio")
    private String message;

    @NotEmpty(message = "Preenchimento obrigatótio")
    private String password;

    public DecodeMessageRequest(String message, String password) {
        this.message = message;
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
