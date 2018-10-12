package api.exceptions;

public class ArgumentNotValidException extends RuntimeException {

    private static final String DESCRIPTION = "Argument Not Valid Exception";

    public ArgumentNotValidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
