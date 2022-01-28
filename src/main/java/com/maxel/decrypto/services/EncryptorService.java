package com.maxel.decrypto.services;

import com.maxel.decrypto.constants.Constants;
import com.maxel.decrypto.domain.MessageRequest;
import com.maxel.decrypto.dto.DencryptedMessageDTO;
import com.maxel.decrypto.dto.EncryptedMessageDTO;
import com.maxel.decrypto.repositories.MessageRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EncryptorService {

    @Autowired
    private MessageRequestRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private StringBuilder msg;
    private String received;
    private char[] key;
    private Integer cont;

    public EncryptedMessageDTO code(MessageRequest request) {
        initializeVars(request);
        String encodedPass = encodePass(request);

        for(char letter : received.toLowerCase().toCharArray())
        {
           if(Character.isWhitespace(letter))
           {
               msg.append(randomUpperCaseChar());
           }
           else
           {
               var index = Constants.NORMALLETTER.indexOf(letter) + indexOfLetterKey(key);
               msg.append(Constants.RANDOMLETTER.toCharArray()[index]);
           }
        }

        MessageRequest encoded = repository.save(new MessageRequest(msg.toString(), encodedPass));
        return new EncryptedMessageDTO(encoded);
    }

    public DencryptedMessageDTO decode(MessageRequest request) {
        initializeVars(request);

       for(char caracter : received.toCharArray())
       {
            if(Constants.CHARSUPPER.contains(caracter))
            {
                msg.append(" ");
            }
            else
            {
                var index = Constants.RANDOMLETTER.indexOf(caracter) - indexOfLetterKey(key);
                index = index < 0 ? index * -1 : index;
                msg.append(Constants.NORMALLETTER.toCharArray()[index]);
            }
       }

       return new DencryptedMessageDTO(msg.toString());
    }

    private char randomUpperCaseChar() {
        var random = new Random();
        var number = random.nextInt(8);
        return Constants.CHARSUPPER.get(number);
    }

    private int indexOfLetterKey(char[] key) {
        if(cont.equals(key.length)) {
            cont = 0;
        }

        var letter = Constants.NORMALLETTER.indexOf(key[cont]);
        cont++;
        return letter;
    }

    private void initializeVars(MessageRequest request) {
        msg = new StringBuilder();
        received = request.getMessage();
        key = request.getPassword().trim().toLowerCase().toCharArray();
        cont = 0;
    }

    private String encodePass(MessageRequest request) {
        return bCryptPasswordEncoder.encode(request.getPassword());
    }
}
