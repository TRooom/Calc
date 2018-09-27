package calculator;

@SuppressWarnings("serial")
public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super("division by zero");
    }
}
