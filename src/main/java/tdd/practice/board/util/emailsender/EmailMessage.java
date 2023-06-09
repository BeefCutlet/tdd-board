package tdd.practice.board.util.emailsender;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailMessage {

    private String recipient;
    private String subject;
    private String content;
}
