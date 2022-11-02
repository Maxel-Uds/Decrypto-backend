package com.maxel.decrypto.services.impl;

import com.maxel.decrypto.constants.Constants;
import com.maxel.decrypto.resources.request.MessageRequest;
import com.maxel.decrypto.resources.response.MessageResponse;
import com.maxel.decrypto.services.EncryptorService;
import com.maxel.decrypto.services.MessageService;
import com.maxel.decrypto.services.PasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class DecodeServiceImpl implements EncryptorService {

    private final PasswordService passwordService;
    private final MessageService messageService;
    private StringBuilder msg;
    private String received;
    private char[] key;
    private Integer cont;

    @Override
    public MessageResponse processMessage(MessageRequest request) {
        log.info("===== Processando a mensagem ======");

        var message = messageService.findMessageById(request.getId());
        initializeVars(request.getPassword(), request.getMessage());

        if(passwordService.isValidPassword(message.getPassword(), request.getPassword())) {
            for(char caracter : received.toCharArray())
            {
                var ignore = Constants.CHARS_UPPER.contains(caracter) ? msg.append(" ") : appendChar(caracter);
            }

            messageService.deleteMessageById(message.getId());
            return new MessageResponse(msg.toString());
        }
        else {
            return generateRandomMessageDTO(request.getMessage());
        }
    }

    private void initializeVars(String password, String message) {
        msg = new StringBuilder();
        received = message;
        key = password.trim().toLowerCase().toCharArray();
        cont = 0;
    }

    private Integer randomIndex(Integer number) {
        var random = new Random();
        return random.nextInt(number);
    }

    private StringBuilder appendChar(Character character) {
        var index = Constants.RANDOM_LETTER.indexOf(character) + (indexOfLetterKey(key) * Constants.DECODE);
        index = index < 0 ? index * -1 : index;
        return msg.append(Constants.NORMAL_LETTER.toCharArray()[index]);
    }

    private int indexOfLetterKey(char[] key) {
        if(cont.equals(key.length)) {
            cont = 0;
        }

        var index = Constants.NORMAL_LETTER.indexOf(key[cont]);
        cont++;
        return index;
    }

    private MessageResponse generateRandomMessageDTO(String message) {
        for(char caracter : message.toCharArray()) {
            msg.append(Constants.RANDOM_LETTER.toCharArray()[randomIndex(Constants.RANDOM_LETTER.length())]);
        }

        return new MessageResponse(msg.toString());
    }
}
