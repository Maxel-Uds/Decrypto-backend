package com.maxel.decrypto.resources.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class MessageResponse implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
