package tdd.practice.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Board {
    private Integer boardNo;
    private Integer boardWriter;
    private String boardTitle;
    private String boardContent;
    private String boardCategory;
    private String boardUpload;
    private Integer boardViews;
    private Integer boardSecret;
    private Integer boardGroup;
    private Integer BoardOrder;
    private Integer BoardLevel;
    private Member writer;
}
