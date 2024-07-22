package com.maxel.decrypto.resources.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

@Getter
@Setter
public class MessageRequest implements Serializable {

    @JsonIgnore
    private String id;
    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 20, message = "O tamanho da mensagem deve ser de no mínimo 20 caracteres")
    private String message;

    @NotEmpty(message = "Preenchimento obrigatótio")
    @Length(min = 5, message = "O tamanho da chave deve ser de no mínimo 5 caracteres")
    private String password;

    public MessageRequest(String message, String password) {
        this.message = message;
        this.password = password;
    }
}
