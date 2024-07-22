package com.maxel.decrypto.resources.exceptions;

import java.io.Serializable;

public record FieldMessage(String fieldName, String message) implements Serializable {
}
