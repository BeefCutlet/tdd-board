package tdd.practice.board.email;

import org.junit.jupiter.api.Test;
import tdd.practice.board.util.EmailMessage;
import tdd.practice.board.util.EmailSender;

import javax.mail.MessagingException;

public class EmailSendTest {

    private EmailSender emailSender = new EmailSender();

    @Test
    void emailSend() throws MessagingException {
        EmailMessage emailMessage = EmailMessage.builder()
                                        .recipient("seuk7316@gmail.com")
                                        .subject("테스트 제목")
                                        .content("테스트 내용")
                                        .build();
        emailSender.sendMail(emailMessage);
    }
}
