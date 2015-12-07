package be.ordina.iot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to throw when a board failure occurred!
 */
//TODO: Implement a way to return the actual exception body/message instead of giving a static reason!
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cannot pulse LED! Please check hardware and software configuration!")
public class BoardFailureException extends Exception {
    //Wrapper.
}