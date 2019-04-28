package pl.net.rogala.converter.pressure;

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
public class PressureConverterController {

    private PressureConverterService pressureConverterService;

    @Autowired
    public PressureConverterController(PressureConverterService pressureConverterService) {
        this.pressureConverterService = pressureConverterService;
    }

    @ModelAttribute("pressureSingleSelectAllValues")
    public String[] getPressureSingleSelectAllValues() {
        return new String[]{
                "Pa", "hPa", "atm", "bar", "mmHg"
        };
    }

    @GetMapping("/pressure")
    public String showPressureConverterForm(Model model) {
        model.addAttribute("pressureForm", new ConverterForm());
        return "temperature/pressureConvForm";
    }

    @PostMapping("/pressure")
    public String handlePressureConverterForm(
            @ModelAttribute("pressureForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/pressureConvForm";
        } else {
            pressureConverterService.convertPressure(converterForm, model);
            return "temperature/pressureResult";
        }
    }
}
