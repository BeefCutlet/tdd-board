package tdd.practice.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Integer memberNo;
    private String memberEmail;
    private String memberNickname;
    private String memberPassword;
    private String memberRegistrationDay;
    private String memberLastLogin;
    private Integer memberAuth;
}
