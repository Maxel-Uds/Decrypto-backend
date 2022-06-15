package com.maxel.decrypto.services;

import com.maxel.decrypto.constants.Constants;
import com.maxel.decrypto.domain.Message;
import com.maxel.decrypto.repositories.MessageRequestRepository;
import com.maxel.decrypto.resources.request.MessageRequest;
import com.maxel.decrypto.resources.response.MessageResponse;
import com.maxel.decrypto.services.exceptions.ObjectNotFoundException;
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

    public Message findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> { throw new ObjectNotFoundException("Nenhuma mensagem foi encontrada com o ID: " + id); });
    }

    public MessageResponse code(MessageRequest request) {
        initializeVars(request);

        for(char caracter : received.toLowerCase().toCharArray())
        {
            var ignore = Character.isWhitespace(caracter) ? msg.append(Constants.CHARS_UPPER.get(randomIndex(8))) : appendChar(Constants.NORMAL_LETTER, Constants.RANDOM_LETTER, Constants.CODE, caracter);
        }

        Message encoded = repository.save(new Message(msg.toString(), encodePass(request.getPassword())));
        return new MessageResponse(encoded.getId(), encoded.getMessage());
    }

    public MessageResponse decode(MessageRequest request, String id) {
        var message = findById(id);
        initializeVars(request);

        if(isValidPassword(message.getPassword(), request.getPassword())) {
            for(char caracter : received.toCharArray())
            {
                var ignore = Constants.CHARS_UPPER.contains(caracter) ? msg.append(" ") : appendChar(Constants.RANDOM_LETTER, Constants.NORMAL_LETTER, Constants.DECODE, caracter);
            }

            repository.deleteById(message.getId());
            return new MessageResponse(msg.toString());
        }
        else {
            return generateRandomMessageDTO(request.getMessage());
        }
    }

    private Boolean isValidPassword(String objectPass, String receivedPass) {
        return bCryptPasswordEncoder.matches(receivedPass, objectPass);
    }

    private Integer randomIndex(Integer number) {
        var random = new Random();
        return random.nextInt(number);
    }

    private int indexOfLetterKey(char[] key) {
        if(cont.equals(key.length)) {
            cont = 0;
        }

        var index = Constants.NORMAL_LETTER.indexOf(key[cont]);
        cont++;
        return index;
    }

    private void initializeVars(MessageRequest request) {
        msg = new StringBuilder();
        received = request.getMessage();
        key = request.getPassword().trim().toLowerCase().toCharArray();
        cont = 0;
    }

    private String encodePass(String pass) {
        return bCryptPasswordEncoder.encode(pass);
    }

    private MessageResponse generateRandomMessageDTO(String message) {
        for(char caracter : message.toCharArray()) {
            msg.append(Constants.RANDOM_LETTER.toCharArray()[randomIndex(96)]);
        }

        return new MessageResponse(msg.toString());
    }

    private StringBuilder appendChar(String sum, String pick, Integer action, Character character) {
        var index = sum.indexOf(character) + (indexOfLetterKey(key) * action);
        index = index < 0 ? index * -1 : index;
        return msg.append(pick.toCharArray()[index]);
    }
}
