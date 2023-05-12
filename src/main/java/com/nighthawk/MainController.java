package com.nighthawk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The MainController class handles the request for the home page.
 * The home page is the index.html file.
 */
@Controller
public class MainController {

    /**
     * Handles the request for the home page.
     * @return index.html
     */
    @GetMapping
    public String showHomePage() {
        return "index";
    }
}
