package com.maxel.decrypto.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Message {

    @Id
    private String id;
    private String message;
    private String password;

    public Message() {}

    public Message(String msg, String pass) {
        this.message = msg;
        this.password = pass;
        this.id = UUID.randomUUID().toString();
    }
}
