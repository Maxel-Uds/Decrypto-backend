package com.maxel.decrypto.services;

import com.maxel.decrypto.constants.Constants;
import com.maxel.decrypto.domain.MessageRequest;
import com.maxel.decrypto.dto.MessageDTO;
import com.maxel.decrypto.repositories.MessageRequestRepository;
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
        Optional<MessageRequest> request = repository.findById(id);
        return request.orElseThrow(() -> { throw new ObjectNotFoundException("Nenhuma mensagem foi encontrada com o ID: " + id); });
    }

    public MessageDTO code(MessageRequest request) {
        initializeVars(request);

        for(char letter : received.toLowerCase().toCharArray())
        {
           if(Character.isWhitespace(letter))
           {
               msg.append(Constants.CHARSUPPER.get(randomIndex(8)));
           }
           else
           {
               var index = Constants.NORMALLETTER.indexOf(letter) + indexOfLetterKey(key);
               msg.append(Constants.RANDOMLETTER.toCharArray()[index]);
           }
        }

        MessageRequest encoded = repository.save(new MessageRequest(msg.toString(), encodePass(request), request.getMessageCode()));
        return new MessageDTO(encoded);
    }

    public MessageDTO checkPassword(MessageRequest request) {
        var obj = findById(request.getMessageCode());
        initializeVars(request);

        if(bCryptPasswordEncoder.matches(request.getPassword(), obj.getPassword()))
        {
            return decode(request);
        }
        else
        {
            for(char caracter : request.getMessage().toCharArray()) {
                msg.append(Constants.RANDOMLETTER.toCharArray()[randomIndex(96)]);
            }

            return new MessageDTO(msg.toString());
        }
    }

    public MessageDTO decode(MessageRequest request) {
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

       repository.deleteById(request.getMessageCode());
       return new MessageDTO(msg.toString());
    }

    private Integer randomIndex(Integer number) {
        var random = new Random();
        return random.nextInt(number);
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
