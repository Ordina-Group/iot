package be.ordina.iot.services;

import be.ordina.iot.exceptions.InputValidationException;
import be.ordina.iot.model.RandomResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contains our random generator services.
 */
@RestController
public class RandomServices {

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public String random() {
        return "Please use POST with the following parameters: minimum:Integer, maximum:Integer";
    }

    @RequestMapping(value = "/random", method = RequestMethod.POST)
    public RandomResult random(@RequestParam(value="minimum", defaultValue="0") Integer minimum, @RequestParam(value="maximum", defaultValue="100") Integer maximum) throws InputValidationException {
        return new RandomResult(minimum, maximum);
    }
}