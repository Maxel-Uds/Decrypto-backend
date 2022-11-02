package com.maxel.decrypto.resources;

import com.maxel.decrypto.resources.request.MessageRequest;
import com.maxel.decrypto.resources.response.MessageResponse;
import com.maxel.decrypto.services.EncryptorService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/encrypt")
public class EncryptorResource {

    private final EncryptorService decodeService;
    private final EncryptorService codeService;

    @ApiOperation(value = "Endpoint que serve para subir a aplicação hospedada no Heroku")
    @GetMapping(value = "/up")
    public ResponseEntity<String> upApplication() {
        return ResponseEntity.ok().body("Application is up!");
    }

    @ApiOperation(value = "Criptografa uma mensagem digitada pelo usuário baseada em uma senha escolhida por ele")
    @PostMapping(value = "/code")
    public ResponseEntity<MessageResponse> codeText(@Valid @RequestBody MessageRequest request) {
        log.info("===== Iniciando a codificação de mensagem ======");
        var codeMessage = codeService.processMessage(request);
        return ResponseEntity.ok().body(codeMessage);
    }

    @ApiOperation(value = "Descriptografa uma mensagem informada pelo usuário se a senha informada for a correta")
    @PostMapping(value = "/decode/{messageId}")
    public ResponseEntity<MessageResponse> decodeText(@Valid @RequestBody MessageRequest request, @PathVariable String messageId) {
        log.info("===== Iniciando a decodificação de mensagem ======");
        request.setId(messageId);
        var decodedMessage = decodeService.processMessage(request);
        return ResponseEntity.ok().body(decodedMessage);
    }
}
