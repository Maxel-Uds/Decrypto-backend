package com.maxel.decrypto.services;

import com.maxel.decrypto.resources.request.MessageRequest;
import com.maxel.decrypto.resources.response.MessageResponse;

public interface EncryptorService {

    MessageResponse processMessage(MessageRequest request);
}
