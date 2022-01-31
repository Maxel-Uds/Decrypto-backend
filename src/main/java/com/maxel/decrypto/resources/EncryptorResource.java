package com.maxel.decrypto.resources;

import com.maxel.decrypto.domain.MessageRequest;
import com.maxel.decrypto.dto.MessageDTO;
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

    @ApiOperation(value = "Criptografa uma mensagem digitada pelo usuário baseada em uma senha escolhida por ele")
    @PostMapping(value = "/code")
    public ResponseEntity<MessageDTO> codeText(@Valid @RequestBody MessageRequest request) {
        var codeMessage = service.code(request);
        return ResponseEntity.ok().body(codeMessage);
    }

    @ApiOperation(value = "Descriptografa uma mensagem informada pelo usuário se a senha informada for a correta")
    @GetMapping(value = "/decode")
    public ResponseEntity<MessageDTO> decodeText(@Valid @RequestBody MessageRequest request) {
        var codeMessage = service.checkPassword(request);
        return ResponseEntity.ok().body(codeMessage);
    }
}
