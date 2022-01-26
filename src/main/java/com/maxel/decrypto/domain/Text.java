package com.maxel.decrypto.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class Text implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 20, message = "O tamanho da mensagem deve ser de no mínimo 20 caracteres")
    private String message;

    public Text() {}

    public Text(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
