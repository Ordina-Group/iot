package be.ordina.iot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class will handle routing.
 */
@Controller
public class Router {

    /**
     * This routes the "/" to the index page.
     *
     * @return The view to be displayed.
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}