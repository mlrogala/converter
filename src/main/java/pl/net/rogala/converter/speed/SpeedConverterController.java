package pl.net.rogala.converter.speed;

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
public class SpeedConverterController {

    private SpeedConverterService speedConverterService;

    @Autowired
    public SpeedConverterController(SpeedConverterService speedConverterService) {
        this.speedConverterService = speedConverterService;
    }

    @ModelAttribute("speedRadioAllValues")
    public String[] getSpeedRadioAllValues() {
        return new String[]{
                "m/s", "km/h", "mph"
        };
    }

    @GetMapping("/speed")
    public String showSpeedConverterForm(Model model) {
        model.addAttribute("speedForm", new ConverterForm());
        return "temperature/speedConvForm";
    }

    @PostMapping("/speed")
    public String handleSpeedConverterForm(
            @ModelAttribute("speedForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/speedConvForm";
        } else {
            speedConverterService.convertSpeed(converterForm, model);

            return "temperature/speedResult";
        }
    }
}
