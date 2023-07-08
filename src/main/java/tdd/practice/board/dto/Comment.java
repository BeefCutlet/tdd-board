package tdd.practice.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comment {

    Integer commentNo;
    String commentContent;
    Integer commentWriter;
    String commentUpload;
    Integer commentBoard;
    Integer commentOrder;
    Integer commentLevel;
    String memberNickname;
}
