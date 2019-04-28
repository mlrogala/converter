package pl.net.rogala.converter.length;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

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

    @GetMapping("/length")
    public String showLengthConverterForm(Model model) {
        model.addAttribute("lengthForm", new ConverterForm());
        return "temperature/lengthConvForm";
    }

    @PostMapping("/length")
    public String handleLengthConverterForm(
            @ModelAttribute("lengthForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/lengthConvForm";
        } else {
            lengthConverterService.convertLength(converterForm, model);

            return "temperature/lengthResult";
        }
    }
}
