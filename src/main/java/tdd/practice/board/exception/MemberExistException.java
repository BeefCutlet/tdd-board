package tdd.practice.board.exception;

public class MemberExistException extends RuntimeException {

    public MemberExistException() {
    }

    public MemberExistException(String message) {
        super(message);
    }
}
