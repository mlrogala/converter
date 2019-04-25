package pl.net.rogala.converter.temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("tempForm", new TemperatureConverterForm());
        return "temperature/tempConvForm";
    }

    @PostMapping("/temperature")
    public String handleTemperatureConverterForm(
            @ModelAttribute("tempForm") @Valid TemperatureConverterForm temperatureConverterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/tempConvForm";
        } else {
            TemperaturePOJO temperaturePOJO;
            String incomeUnit = temperatureConverterForm.getIncomeUnit();
            String outcomeUnit = temperatureConverterForm.getOutcomeUnit();
            double value = Double.valueOf(temperatureConverterForm.getValue());
            if (incomeUnit.equals(outcomeUnit)) {
                temperaturePOJO = new TemperaturePOJO(value);
                model.addAttribute("tempResult", temperaturePOJO);
            } else if (incomeUnit.equals("Celsjusz")) {
                if (outcomeUnit.equals("Kelvin")) {
                    temperaturePOJO = temperatureConverterService.convertCelciusToKelvin(new TemperaturePOJO(value));
                    model.addAttribute("tempResult", temperaturePOJO);
                } else if (outcomeUnit.equals("Fahrenheit")) {
                    temperaturePOJO = temperatureConverterService.convertCelsiusToFahrenheit(new TemperaturePOJO(value));
                    model.addAttribute("tempResult", temperaturePOJO);
                }
                return "temperature/tempResult";
            } else if (incomeUnit.equals("Kelvin")) {
                if (outcomeUnit.equals("Celsjusz")) {
                    temperaturePOJO = temperatureConverterService.convertKelvinToCelsius(new TemperaturePOJO(value));
                    model.addAttribute("tempResult", temperaturePOJO);
                } else if (outcomeUnit.equals("Fahrenheit")) {
                    temperaturePOJO = temperatureConverterService.convertKelvinToFahrenheit(new TemperaturePOJO(value));
                    model.addAttribute("tempResult", temperaturePOJO);
                }
                return "temperature/tempResult";
            } else if (incomeUnit.equals("Fahrenheit")) {
                if (outcomeUnit.equals("Celsjusz")) {
                    temperaturePOJO = temperatureConverterService.convertFahrenheitToCelsius(new TemperaturePOJO(value));
                    model.addAttribute("tempResult", temperaturePOJO);
                } else if (outcomeUnit.equals("Kelvin")) {
                    temperaturePOJO = temperatureConverterService.convertFahrenheitToKelvin(new TemperaturePOJO(value));
                    model.addAttribute("tempResult", temperaturePOJO);
                }
                return "temperature/tempResult";

            }
        }
        return "temperature/tempResult";

    }
}