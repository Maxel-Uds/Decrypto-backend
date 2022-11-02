package service;

import com.maxel.decrypto.domain.Message;
import com.maxel.decrypto.resources.request.MessageRequest;
import com.maxel.decrypto.resources.response.MessageResponse;
import com.maxel.decrypto.services.EncryptorService;
import com.maxel.decrypto.services.MessageService;
import com.maxel.decrypto.services.PasswordService;
import com.maxel.decrypto.services.impl.DecodeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class DecodeServiceTest {

    private static final String CODED_PASS = "63y6sb763bnd";
    private static final String DECODED_PASS = "senha1234";
    private static final String CODED_MESSAGE = "|:}^0ƏՆ;FƩɸIƩ~^ʘՅI*Ճ5ՀΦ[7Ճ";
    private static final String DECODED_MESSAGE = "messagem de teste unitário";
    private static final String MESSAGE_ID = "73454685-3108-4ce4-8624-a1fa8e08a40e";

    private PasswordService passwordService;
    private MessageService messageService;
    private EncryptorService decodeService;

    @BeforeEach
    void setUp() {
        passwordService = mock(PasswordService.class);
        messageService = mock(MessageService.class);

        decodeService = new DecodeServiceImpl(passwordService, messageService);
    }

    @Test
    @DisplayName("Deve decodificar uma mensagem com sucesso")
    void shouldDecodeMessageWithSuccess() {
        var request = new MessageRequest(CODED_MESSAGE, DECODED_PASS);
        request.setId(MESSAGE_ID);
        var response = new MessageResponse(DECODED_MESSAGE);
        var message = new Message(CODED_MESSAGE, CODED_PASS);

        doReturn(message)
                .when(messageService)
                .findMessageById(anyString());

        doReturn(true)
                .when(passwordService)
                .isValidPassword(anyString(), anyString());

        doNothing()
                .when(messageService)
                .deleteMessageById(anyString());

        assertEquals(decodeService.processMessage(request).getMessage(), response.getMessage());

        verify(passwordService, times(1)).isValidPassword(anyString(), anyString());
        verify(messageService, times(1)).findMessageById(anyString());
        verify(messageService, times(1)).deleteMessageById(anyString());
    }

    @Test
    @DisplayName("Deve retornar uma mensagem aleatória se a senha estiver incorreta")
    void shouldReturnRandomMessageIfPassIsIncorrect() {
        var request = new MessageRequest(CODED_MESSAGE, DECODED_PASS);
        request.setId(MESSAGE_ID);
        var response = new MessageResponse(CODED_MESSAGE);
        var message = new Message(CODED_MESSAGE, CODED_PASS);

        doReturn(message)
                .when(messageService)
                .findMessageById(anyString());

        doReturn(false)
                .when(passwordService)
                .isValidPassword(anyString(), anyString());

        assertEquals(decodeService.processMessage(request).getClass(), response.getClass());

        verify(passwordService, times(1)).isValidPassword(anyString(), anyString());
        verify(messageService, times(1)).findMessageById(anyString());
    }
}
