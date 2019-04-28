package pl.net.rogala.converter.mass;

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
public class MassConverterController {

    private MassConverterService massConverterService;

    @Autowired
    public MassConverterController(MassConverterService massConverterService) {
        this.massConverterService = massConverterService;
    }

    @ModelAttribute("massSingleSelectAllValues")
    public String[] getSingleSelectAllValues() {
        return new String[]{
                "kilogram", "tona", "funt", "uncja"
        };
    }

    @GetMapping("/mass")
    public String showTemperatureConverterForm(Model model) {
        model.addAttribute("massForm", new ConverterForm());
        return "temperature/massConvForm";
    }

    @PostMapping("/mass")
    public String handleTemperatureConverterForm(
            @ModelAttribute("massForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/massConvForm";
        } else {
            massConverterService.convertMass(converterForm, model);
            return "temperature/massResult";
        }
    }
}
