package be.ordina.iot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to throw when the input validation failed!
 */
//TODO: Implement a way to return the actual exception body/message instead of giving a static reason!
@ResponseStatus(code = HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST, reason = "Validation for the given parameters failed!")
public class InputValidationException extends Exception {

    private String message;
    private Integer minimum;
    private Integer maximum;

    /**
     * Cobstructor that takes extra information about the numbers that were validated.
     *
     * @param message The message describing the exception.
     * @param minimum The minimum value.
     * @param maximum The maximim value.
     */
    public InputValidationException(String message, Integer minimum, Integer maximum) {
        super(message);
        this.message = message;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public String getMessage() {
        return toString();
    }

    @Override
    public String toString() {
        return "Error: " + message + "\nMinimum value: " + minimum + "\nMaximum value: " + maximum;
    }
}