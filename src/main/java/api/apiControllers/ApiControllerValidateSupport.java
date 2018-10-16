package api.apiControllers;

import api.exceptions.ArgumentNotValidException;

public abstract class ApiControllerValidateSupport {

    public abstract void validate(Object object);

    protected void validateNotNull(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }

    protected void validateNotEquals(Object aProperty, Object anotherProperty, String message) {
        if (!aProperty.equals(anotherProperty)) {
            throw new ArgumentNotValidException(message);
        }
    }

}
