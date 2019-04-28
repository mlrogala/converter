package pl.net.rogala.converter.temperature;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class TemperatureConverterService {

    public BasicPOJO convertCelciusToKelvin(BasicPOJO temperatureCelcius){
        return new BasicPOJO(temperatureCelcius.getValue()+273.15);
    }

    public BasicPOJO convertKelvinToCelsius(BasicPOJO temperatureKelvin){
       return new BasicPOJO(temperatureKelvin.getValue()-273.15);
    }

    public BasicPOJO convertCelsiusToFahrenheit(BasicPOJO temperatureCelsius){
        return new BasicPOJO((temperatureCelsius.getValue()*1.8)+32);
    }

    public BasicPOJO convertFahrenheitToCelsius(BasicPOJO temperatureFahrenheit){
        return new BasicPOJO((temperatureFahrenheit.getValue()-32)/1.8);
    }

    public BasicPOJO convertKelvinToFahrenheit(BasicPOJO temperatureKelvin){
        return new BasicPOJO((temperatureKelvin.getValue()*1.8)-459.67);
    }

    public BasicPOJO convertFahrenheitToKelvin(BasicPOJO temperatureFahrenheit){
        return new BasicPOJO((temperatureFahrenheit.getValue()+459.67)*5/9);
    }

    public void convertTemperature(@Valid @ModelAttribute("tempForm") ConverterForm converterForm, Model model) {
        BasicPOJO temperaturePOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            temperaturePOJO = new BasicPOJO(value);
            model.addAttribute("tempResult", temperaturePOJO);
        } else if (incomeUnit.equals("Celsjusz")) {
            if (outcomeUnit.equals("Kelvin")) {
                temperaturePOJO = convertCelciusToKelvin(new BasicPOJO(value));
                model.addAttribute("tempResult", temperaturePOJO);
            } else if (outcomeUnit.equals("Fahrenheit")) {
                temperaturePOJO = convertCelsiusToFahrenheit(new BasicPOJO(value));
                model.addAttribute("tempResult", temperaturePOJO);
            }
        } else if (incomeUnit.equals("Kelvin")) {
            if (outcomeUnit.equals("Celsjusz")) {
                temperaturePOJO = convertKelvinToCelsius(new BasicPOJO(value));
                model.addAttribute("tempResult", temperaturePOJO);
            } else if (outcomeUnit.equals("Fahrenheit")) {
                temperaturePOJO = convertKelvinToFahrenheit(new BasicPOJO(value));
                model.addAttribute("tempResult", temperaturePOJO);
            }
        } else if (incomeUnit.equals("Fahrenheit")) {
            if (outcomeUnit.equals("Celsjusz")) {
                temperaturePOJO = convertFahrenheitToCelsius(new BasicPOJO(value));
                model.addAttribute("tempResult", temperaturePOJO);
            } else if (outcomeUnit.equals("Kelvin")) {
                temperaturePOJO = convertFahrenheitToKelvin(new BasicPOJO(value));
                model.addAttribute("tempResult", temperaturePOJO);
            }

        }
    }


}
