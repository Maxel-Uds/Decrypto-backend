package com.maxel.decrypto.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class MessageRequest implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 20, message = "O tamanho da mensagem deve ser de no mínimo 20 caracteres")
    private String message;
    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 5, message = "O tamanho da mensagem deve ser de no mínimo 5 caracteres")
    private String key;

    public MessageRequest() {}

    public MessageRequest(String msg, String key) {
        this.message = msg;
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
