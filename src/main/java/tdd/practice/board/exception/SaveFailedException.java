package tdd.practice.board.exception;

public class SaveFailedException extends RuntimeException {

    public SaveFailedException() {
    }

    public SaveFailedException(String message) {
        super(message);
    }
}
