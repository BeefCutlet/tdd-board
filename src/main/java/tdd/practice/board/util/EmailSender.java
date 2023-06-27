package tdd.practice.board.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private JavaMailSender javaMailSender;

    public void sendMail(EmailMessage emailMessage) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        InternetAddress[] recipientAddress = new InternetAddress[1];
        recipientAddress[0] = new InternetAddress(emailMessage.getRecipient());

        mimeMessageHelper.setTo(recipientAddress);
        mimeMessageHelper.setSubject(emailMessage.getSubject());
        mimeMessageHelper.setText(emailMessage.getContent());
        javaMailSender.send(mimeMessage);
    }
}
