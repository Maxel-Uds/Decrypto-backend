package com.maxel.decrypto.services;

import com.maxel.decrypto.constants.Constants;
import com.maxel.decrypto.domain.MessageRequest;
import com.maxel.decrypto.dto.MessageDTO;
import com.maxel.decrypto.repositories.MessageRequestRepository;
import com.maxel.decrypto.services.exceptions.ObjectAlreadyExistException;
import com.maxel.decrypto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public MessageRequest findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> { throw new ObjectNotFoundException("Nenhuma mensagem foi encontrada com o ID: " + id); });
    }

    public MessageDTO code(MessageRequest request) {
        initializeVars(request);
        checkIfExists(request.getId());

        for(char caracter : received.toLowerCase().toCharArray())
        {
           if(Character.isWhitespace(caracter))
           {
               msg.append(Constants.CHARS_UPPER.get(randomIndex(8)));
           }
           else
           {
               appendChar(Constants.NORMAL_LETTER, Constants.RANDOM_LETTER, Constants.CODE, caracter);
           }
        }

        MessageRequest encoded = repository.save(new MessageRequest(msg.toString(), encodePass(request), request.getId()));
        return new MessageDTO(encoded);
    }

    public MessageDTO checkPassword(MessageRequest request) {
        var obj = findById(request.getId());
        initializeVars(request);

        if(bCryptPasswordEncoder.matches(request.getPassword(), obj.getPassword()))
        {
            return decode(request);
        }
        else
        {
            return generateRandomMessageDTO(request.getMessage());
        }
    }

    public MessageDTO decode(MessageRequest request) {
       for(char caracter : received.toCharArray())
       {
            if(Constants.CHARS_UPPER.contains(caracter))
            {
                msg.append(" ");
            }
            else
            {
                appendChar(Constants.RANDOM_LETTER, Constants.NORMAL_LETTER, Constants.DECODE, caracter);
            }
       }

       repository.deleteById(request.getId());
       return new MessageDTO(msg.toString());
    }

    private void checkIfExists(Integer id) {
        repository.findById(id)
                .ifPresent(obj -> { throw new ObjectAlreadyExistException("Já existe uma mensagem utilizando o código escolhido, por favor, use outro!"); });
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

    private String encodePass(MessageRequest request) {
        return bCryptPasswordEncoder.encode(request.getPassword());
    }

    private MessageDTO generateRandomMessageDTO(String message) {
        for(char caracter : message.toCharArray()) {
            msg.append(Constants.RANDOM_LETTER.toCharArray()[randomIndex(96)]);
        }

        return new MessageDTO(msg.toString());
    }

    private void appendChar(String sum, String pick, Integer action, Character character) {
        var index = sum.indexOf(character) + (indexOfLetterKey(key) * action);
        index = index < 0 ? index * -1 : index;
        msg.append(pick.toCharArray()[index]);
    }
}
