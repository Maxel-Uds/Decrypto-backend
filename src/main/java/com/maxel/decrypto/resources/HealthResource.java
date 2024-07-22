package com.maxel.decrypto.resources;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class HealthResource {

    @Operation(summary = "Endpoint que serve para checar o status da aplicação")
    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {
        log.info("===== Status da Aplicação OK! =====");
        return ResponseEntity.ok().body("OK!");
    }
}
