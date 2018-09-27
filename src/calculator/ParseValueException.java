package calculator;

@SuppressWarnings("serial")
public class ParseValueException extends Exception {
    public ParseValueException() {
        super("invalid string format");
    }
    public ParseValueException(String details) {
        super("invalid string format. " + details);
    }
}
