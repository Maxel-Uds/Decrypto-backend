package service;

import com.maxel.decrypto.domain.Message;
import com.maxel.decrypto.resources.request.MessageRequest;
import com.maxel.decrypto.resources.response.MessageResponse;
import com.maxel.decrypto.services.EncryptorService;
import com.maxel.decrypto.services.MessageService;
import com.maxel.decrypto.services.PasswordService;
import com.maxel.decrypto.services.impl.CodeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class CodeServiceTest {

    private static final String CODED_PASS = "63y6sb763bnd";
    private static final String DECODED_PASS = "senha1234";
    private static final String CODED_MESSAGE = "|:}^0ƏՆ;FƩɸIƩ~^ʘՅI*Ճ5ՀΦ[7Ճ";
    private static final String DECODED_MESSAGE = "messagem de teste unitário";
    private static final String MESSAGE_ID = "73454685-3108-4ce4-8624-a1fa8e08a40e";

    private PasswordService passwordService;
    private MessageService messageService;
    private EncryptorService codeService;

    @BeforeEach
    void setUp() {
        passwordService = mock(PasswordService.class);
        messageService = mock(MessageService.class);

        codeService = new CodeServiceImpl(passwordService, messageService);
    }

    @Test
    @DisplayName("Deve codificar uma mensagem com sucesso")
    void shouldCodeMessageWithSuccess() {
        var request = new MessageRequest(DECODED_MESSAGE, DECODED_PASS);
        var response = new MessageResponse(MESSAGE_ID, CODED_MESSAGE);
        var message = new Message(CODED_MESSAGE, CODED_PASS);

        doReturn(CODED_PASS)
                .when(passwordService)
                .encodePass(anyString());

        doReturn(message)
                .when(messageService)
                .saveMessage(any(Message.class));

        assertEquals(codeService.processMessage(request).getMessage(), response.getMessage());

        verify(passwordService, times(1)).encodePass(anyString());
        verify(messageService, times(1)).saveMessage(any(Message.class));
    }
}
