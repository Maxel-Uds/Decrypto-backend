package com.maxel.decrypto.resources.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
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
}
