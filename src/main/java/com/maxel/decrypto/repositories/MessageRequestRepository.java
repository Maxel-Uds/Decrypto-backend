package com.maxel.decrypto.repositories;

import com.maxel.decrypto.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRequestRepository extends JpaRepository<Message, String> {
}
