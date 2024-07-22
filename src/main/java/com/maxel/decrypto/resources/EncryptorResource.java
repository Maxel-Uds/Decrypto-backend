package com.maxel.decrypto.resources;

import com.maxel.decrypto.resources.request.MessageRequest;
import com.maxel.decrypto.resources.response.MessageResponse;
import com.maxel.decrypto.services.EncryptorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class EncryptorResource {

    private final EncryptorService decodeService;
    private final EncryptorService codeService;

    @Operation(summary = "Criptografa uma mensagem digitada pelo usuário baseada em uma senha escolhida por ele")
    @PostMapping(value = "/code")
    public ResponseEntity<MessageResponse> codeText(@Valid @RequestBody MessageRequest request) {
        log.info("===== Iniciando a codificação de mensagem ======");
        var codeMessage = codeService.processMessage(request);
        return ResponseEntity.ok().body(codeMessage);
    }

    @Operation(summary = "Descriptografa uma mensagem informada pelo usuário se a senha informada for a correta")
    @PostMapping(value = "/decode/{messageId}")
    public ResponseEntity<MessageResponse> decodeText(@Valid @RequestBody MessageRequest request, @PathVariable String messageId) {
        log.info("===== Iniciando a decodificação de mensagem ======");
        request.setId(messageId);
        var decodedMessage = decodeService.processMessage(request);
        return ResponseEntity.ok().body(decodedMessage);
    }
}
