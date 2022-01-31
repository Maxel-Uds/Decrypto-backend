package com.maxel.decrypto.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class MessageRequest implements Serializable {

    @Id
    @NotNull(message = "Criar um código numérico para a mensagem é obrigatório")
    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 20, message = "O tamanho da mensagem deve ser de no mínimo 20 caracteres")
    private String message;
    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 5, message = "O tamanho da chave deve ser de no mínimo 5 caracteres")
    private String password;

    public MessageRequest() {}

    public MessageRequest(String msg, String pass, Integer code) {
        this.message = msg;
        this.password = pass;
        this.id = code;
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

    public void setPassword(String pass) {
        this.password = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageRequest)) return false;
        MessageRequest that = (MessageRequest) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
