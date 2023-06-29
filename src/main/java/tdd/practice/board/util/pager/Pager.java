package tdd.practice.board.util.pager;


public class Pager {

    private PageInfo pageInfo;

    public Pager(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public static Pager.Builder builder() {
        return new Pager.Builder();
    }

    public static class Builder {
        private int currentPage = 1;
        private int totalBoard;
        private int pageSize;
        private int blockSize;
        private String keyword;
        private String condition;

        public Builder currentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Builder totalBoard(int totalBoard) {
            this.totalBoard = totalBoard;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder blockSize(int blockSize) {
            this.blockSize = blockSize;
            return this;
        }

        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public Builder condition(String condition) {
            this.condition = condition;
            return this;
        }

        public Pager build() {
            int startRow = (currentPage - 1) * pageSize + 1;
            int endRow = currentPage * pageSize;
            if (endRow > totalBoard) {
                endRow = totalBoard;
            }
            int offset = startRow - 1;

            int totalPage = (int)Math.ceil((double)totalBoard / pageSize);
            int startPage = (currentPage - 1) / blockSize * blockSize + 1;
            int endPage = startPage + blockSize - 1;
            if (endPage > totalPage) {
                endPage = totalPage;
            }

            String searchKeyword = this.keyword;
            String searchCondition = this.condition;

            return new Pager(PageInfo.builder()
                    .offset(offset)
                    .startRow(startRow)
                    .endRow(endRow)
                    .startPage(startPage)
                    .endPage(endPage)
                    .pageSize(pageSize)
                    .keyword(searchKeyword)
                    .condition(searchCondition)
                    .build()
            );
        }
    }
}
