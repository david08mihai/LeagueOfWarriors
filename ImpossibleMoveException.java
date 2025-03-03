package game;

public class ImpossibleMoveException extends IndexOutOfBoundsException {
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
