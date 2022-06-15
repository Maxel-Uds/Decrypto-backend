package com.maxel.decrypto.resources.request;

import com.maxel.decrypto.domain.Message;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class MessageRequest implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 20, message = "O tamanho da mensagem deve ser de no mínimo 20 caracteres")
    private String message;

    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 5, message = "O tamanho da chave deve ser de no mínimo 5 caracteres")
    private String password;

    public MessageRequest(Message request) {
        this.message = request.getMessage();
    }

    public MessageRequest(String message, String password) {
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
