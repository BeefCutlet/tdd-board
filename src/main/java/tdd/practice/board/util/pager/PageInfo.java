package tdd.practice.board.util.pager;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageInfo {

    private int offset;
    private int startRow;
    private int endRow;
    private int startPage;
    private int endPage;
    private int pageSize;
    private String keyword;
    private String condition;
}
