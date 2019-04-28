package pl.net.rogala.converter.mass;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class MassConverterService {

    public BasicPOJO convertKilogramToTone(BasicPOJO kilogramMass) {
        return new BasicPOJO(kilogramMass.getValue() / 1000d);
    }

    public BasicPOJO convertToneToKilogram(BasicPOJO toneMass) {
        return new BasicPOJO(toneMass.getValue() * 1000d);
    }

    public BasicPOJO convertKilogramToOunce(BasicPOJO kilogramMass) {
        return new BasicPOJO(kilogramMass.getValue() * 35.274d);
    }

    public BasicPOJO convertOunceToKilogram(BasicPOJO ounceMass) {
        return new BasicPOJO(ounceMass.getValue() / 35.274d);
    }

    public BasicPOJO convertKilogramToPound(BasicPOJO kilogramMass) {
        return new BasicPOJO(kilogramMass.getValue() * 0.45359237d);
    }

    public BasicPOJO convertPoundToKilogram(BasicPOJO poundMass) {
        return new BasicPOJO(poundMass.getValue() / 0.45359237d);
    }

    public void convertMass(@Valid @ModelAttribute("massForm") ConverterForm converterForm, Model model) {
        BasicPOJO massPOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            massPOJO = new BasicPOJO(value);
            model.addAttribute("massResult", massPOJO);
        } else if (incomeUnit.equals("kilogram")) {
            switch (outcomeUnit) {
                case "tona":
                    massPOJO = convertKilogramToTone(new BasicPOJO(value));
                    model.addAttribute("massResult", massPOJO);
                    break;
                case "funt":
                    massPOJO = convertKilogramToPound(new BasicPOJO(value));
                    model.addAttribute("massResult", massPOJO);
                    break;
                case "uncja":
                    massPOJO = convertKilogramToOunce(new BasicPOJO(value));
                    model.addAttribute("massResult", massPOJO);
                    break;
            }
        } else if (incomeUnit.equals("tona")) {
            switch (outcomeUnit) {
                case "kilogram":
                    massPOJO = convertToneToKilogram(new BasicPOJO(value));
                    model.addAttribute("massResult", massPOJO);
                    break;
                case "funt":
                    massPOJO = convertKilogramToPound(convertToneToKilogram(new BasicPOJO(value)));
                    model.addAttribute("massResult", massPOJO);
                    break;
                case "uncja":
                    massPOJO = convertKilogramToOunce(convertToneToKilogram(new BasicPOJO(value)));
                    model.addAttribute("massResult", massPOJO);
                    break;
            }
        } else if (incomeUnit.equals("funt")) {
            switch (outcomeUnit) {
                case "kilogram":
                    massPOJO = convertPoundToKilogram(new BasicPOJO(value));
                    model.addAttribute("massResult", massPOJO);
                    break;
                case "tona":
                    massPOJO = convertKilogramToTone(convertPoundToKilogram(new BasicPOJO(value)));
                    model.addAttribute("massResult", massPOJO);
                    break;
                case "uncja":
                    massPOJO = convertKilogramToOunce(convertPoundToKilogram(new BasicPOJO(value)));
                    model.addAttribute("massResult", massPOJO);
                    break;
            }
        } else if (incomeUnit.equals("uncja")) {
            switch (outcomeUnit) {
                case "kilogram":
                    massPOJO = convertOunceToKilogram(new BasicPOJO(value));
                    model.addAttribute("massResult", massPOJO);
                    break;
                case "funt":
                    massPOJO = convertKilogramToPound(convertOunceToKilogram(new BasicPOJO(value)));
                    model.addAttribute("massResult", massPOJO);
                    break;
                case "tona":
                    massPOJO = convertKilogramToTone(convertOunceToKilogram(new BasicPOJO(value)));
                    model.addAttribute("massResult", massPOJO);
                    break;
            }
        }
    }
}