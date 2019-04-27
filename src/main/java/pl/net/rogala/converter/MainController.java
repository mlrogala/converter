package pl.net.rogala.converter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String showMainPage() {
        return "temperature/mainPage";
    }
}
