package com.maxel.decrypto.services;

import com.maxel.decrypto.domain.Text;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EncryptorService {

    @Value("${sequence.random.letters}")
    private String randomLetters;
    @Value("${sequence.normal.letters}")
    private String normalLetters;
    private StringBuilder msg;

    public Text code(Text text) {
        msg = new StringBuilder();
        var received = text.getMessage().toCharArray();

        for(int i = 0; i < received.length; i++)
        {
            for(int j = 0; j < normalLetters.length(); j++)
            {
                if(received[i] == normalLetters.toCharArray()[j])
                {
                    msg.append(randomLetters.toCharArray()[j]);
                    break;
                }
                else if(Character.isWhitespace(received[i]))
                {
                    msg.append(".");
                    break;
                }
                else if(Character.isUpperCase(received[i]))
                {
                    msg.append(received[i]);
                    break;
                }
            }
        }

        text.setMessage(msg.toString());
        return text;
    }
}
