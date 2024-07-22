package com.maxel.decrypto.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Message {

    @Id
    private String id;
    private String message;
    private String password;

    public Message(String msg, String pass) {
        this.message = msg;
        this.password = pass;
        this.id = UUID.randomUUID().toString();
    }
}
