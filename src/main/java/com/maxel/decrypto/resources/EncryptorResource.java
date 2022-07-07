package com.maxel.decrypto.resources;

import com.maxel.decrypto.resources.request.CodeMessageRequest;
import com.maxel.decrypto.resources.request.DecodeMessageRequest;
import com.maxel.decrypto.resources.response.MessageResponse;
import com.maxel.decrypto.services.EncryptorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/encrypt")
public class EncryptorResource {

    @Autowired
    private EncryptorService service;

    @ApiOperation(value = "Endpoint que serve para subir a aplicação hospedada no Heroku")
    @PostMapping(value = "/up")
    public ResponseEntity<String> upApplication() {
        return ResponseEntity.ok().body("Application is up!");
    }

    @ApiOperation(value = "Criptografa uma mensagem digitada pelo usuário baseada em uma senha escolhida por ele")
    @PostMapping(value = "/code")
    public ResponseEntity<MessageResponse> codeText(@Valid @RequestBody CodeMessageRequest request) {
        var codeMessage = service.code(request);
        return ResponseEntity.ok().body(codeMessage);
    }

    @ApiOperation(value = "Descriptografa uma mensagem informada pelo usuário se a senha informada for a correta")
    @PostMapping(value = "/decode/{messageId}")
    public ResponseEntity<MessageResponse> decodeText(@Valid @RequestBody DecodeMessageRequest request, @PathVariable String messageId) {
        var decodedMessage = service.decode(request, messageId);
        return ResponseEntity.ok().body(decodedMessage);
    }
}
