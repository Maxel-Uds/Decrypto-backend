package com.maxel.decrypto.repositories;

import com.maxel.decrypto.domain.MessageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRequestRepository extends JpaRepository<MessageRequest, Integer> {
}
