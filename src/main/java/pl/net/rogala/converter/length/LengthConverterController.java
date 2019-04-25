package pl.net.rogala.converter.length;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LengthConverterController {

    private LengthConverterService lengthConverterService;

    @Autowired
    public LengthConverterController(LengthConverterService lengthConverterService) {
        this.lengthConverterService = lengthConverterService;
    }

    @ModelAttribute("lengthRadioAllValues")
    public String[] getLengthRadioAllValues() {
        return new String[]{
                "metr", "cal", "stopa", "jard", "mila", "mila morska"
        };
    }
}
