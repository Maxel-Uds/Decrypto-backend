package com.maxel.decrypto.services;

import com.maxel.decrypto.constants.Constants;
import com.maxel.decrypto.domain.MessageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;

@Service
public class EncryptorService {

    private StringBuilder msg;
    private IntStream received;
    private char[] key;
    private int cont;

    public String code(MessageRequest request) {
        initializeVars(request);

        received.forEach(letter -> {
           if(Character.isWhitespace(letter))
           {
               msg.append(randomUpperCaseChar());
           }
           else
           {
               var index = Constants.NORMALLETTER.indexOf(letter) + indexOfLetterKey(key);
               msg.append(Constants.RANDOMLETTER.toCharArray()[index]);
           }
        });

        return msg.toString();
    }

    private char randomUpperCaseChar() {
        var random = new Random();
        var number = random.nextInt(8);
        return Constants.CHARSUPPER[number];
    }

    private int indexOfLetterKey(char[] key) {
        if(cont >= key.length) {
            cont = 0;
        }

        var letter = Constants.NORMALLETTER.indexOf(key[cont]);
        cont++;
        return letter;
    }

    private void initializeVars(MessageRequest request) {
        msg = new StringBuilder();
        received = request.getMessage().toLowerCase().chars();
        key = request.getKey().trim().toLowerCase().toCharArray();
        cont = 0;
    }

}
