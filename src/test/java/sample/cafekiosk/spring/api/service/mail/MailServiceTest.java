package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHitsoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHitsoryRepository mailSendHitsoryRepository;

    @InjectMocks
    private MailService mailService;

    @Test
    @DisplayName("메일 전송 테스트")
    void test() {
        // given
        given(mailService.sendMail(anyString(), anyString(), anyString(), anyString())).willReturn(true);

        // when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        assertThat(result).isTrue();
        verify(mailSendHitsoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}