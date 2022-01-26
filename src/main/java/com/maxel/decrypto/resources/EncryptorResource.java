package com.maxel.decrypto.resources;

import com.maxel.decrypto.domain.Text;
import com.maxel.decrypto.services.EncryptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/encrypt")
public class EncryptorResource {

    @Autowired
    private EncryptorService service;

    @PostMapping(value = "/code")
    public ResponseEntity<Text> codeText(@Valid @RequestBody Text text) {
        Text codeMessage = service.code(text);
        return ResponseEntity.ok().body(codeMessage);
    }
}
