package pl.net.rogala.converter.temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Controller
public class TemperatureConverterController {

    private TemperatureConverterService temperatureConverterService;

    @Autowired
    public TemperatureConverterController(TemperatureConverterService temperatureConverterService) {
        this.temperatureConverterService = temperatureConverterService;
    }

    @ModelAttribute("singleSelectAllValues")
    public String[] getSingleSelectAllValues() {
        return new String[]{
                "Celsjusz", "Kelvin", "Fahrenheit"
        };
    }


    @GetMapping("/temperature")
    public String showTemperatureConverterForm(Model model) {
        model.addAttribute("tempForm", new ConverterForm());
        return "temperature/tempConvForm";
    }

    @PostMapping("/temperature")
    public String handleTemperatureConverterForm(
            @ModelAttribute("tempForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/tempConvForm";
        } else {
            temperatureConverterService.convertTemperature(converterForm, model);

            return "temperature/tempResult";
        }
    }


}