package tdd.practice.board.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.practice.board.util.emailsender.EmailMessage;
import tdd.practice.board.util.emailsender.EmailSender;

import javax.mail.MessagingException;

@SpringBootTest
public class EmailSendTest {

    @Autowired
    private EmailSender emailSender;

    @Value("${email.recipient}")
    private String emailRecipient;

    @Test
    void emailSend() throws MessagingException {
        EmailMessage emailMessage = EmailMessage.builder()
                                        .recipient(emailRecipient)
                                        .subject("테스트 제목")
                                        .content("테스트 내용")
                                        .build();
        emailSender.sendMail(emailMessage);
    }
}
