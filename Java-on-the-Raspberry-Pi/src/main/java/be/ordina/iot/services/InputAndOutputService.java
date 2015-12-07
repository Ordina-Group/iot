package be.ordina.iot.services;

import be.ordina.iot.board.RaspberryBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This service provides some endpoints to experiment with GPIO.
 */
@RestController
public class InputAndOutputService {

    @Autowired
    private RaspberryBoard board;

    /**
     * Will pulse the 4'th pin of the GPIO for one whole second.
     */
    @RequestMapping(value = "/pulse", method = RequestMethod.GET)
    public void pulseOutput() throws Exception {
        board.ledPulse(1000L);
    }
}