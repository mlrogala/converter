package pl.net.rogala.converter.length;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class LengthConverterService {

    public BasicPOJO convertMeterToInch(BasicPOJO lengthMeter) {
        return new BasicPOJO(lengthMeter.getValue() / 0.0254d);
    }

    public BasicPOJO convertMeterToFoot(BasicPOJO lengthMeter) {
        return new BasicPOJO(lengthMeter.getValue() * 3.2808d);
    }

    public BasicPOJO convertMeterToYard(BasicPOJO lengthMeter) {
        return new BasicPOJO(lengthMeter.getValue() * 1.09361329833771d);
    }

    public BasicPOJO convertMeterToMile(BasicPOJO lengthMeter) {
        return new BasicPOJO(lengthMeter.getValue() / 1609d);
    }

    public BasicPOJO convertMeterToNauticalMile(BasicPOJO lengthMeter) {
        return new BasicPOJO(lengthMeter.getValue() / 1852d);
    }

    public BasicPOJO convertInchToMeter(BasicPOJO lengthInch) {
        return new BasicPOJO(lengthInch.getValue() * 0.0254d);
    }

    public BasicPOJO convertFootToMeter(BasicPOJO lengthFoot) {
        return new BasicPOJO(lengthFoot.getValue() / 3.2808d);
    }

    public BasicPOJO convertYardToMeter(BasicPOJO lengthYard) {
        return new BasicPOJO(lengthYard.getValue() / 1.09361329833771d);
    }

    public BasicPOJO convertMileToMeter(BasicPOJO lengthMile) {
        return new BasicPOJO(lengthMile.getValue() * 1609d);
    }

    public BasicPOJO convertNauticalMileToMeter(BasicPOJO lengthNauticalMile) {
        return new BasicPOJO(lengthNauticalMile.getValue() * 1852d);
    }

    public void convertLength(@Valid @ModelAttribute("lengthForm") ConverterForm converterForm, Model model) {
        BasicPOJO lengthPOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            lengthPOJO = new BasicPOJO(value);
            model.addAttribute("lengthResult", lengthPOJO);
        } else if (incomeUnit.equals("metr")) {
            switch (outcomeUnit) {
                case "cal":
                    lengthPOJO = convertMeterToInch(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "stopa":
                    lengthPOJO = convertMeterToFoot(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "jard":
                    lengthPOJO = convertMeterToYard(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila":
                    lengthPOJO = convertMeterToMile(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila morska":
                    lengthPOJO = convertMeterToNauticalMile(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
            }
        } else if (incomeUnit.equals("cal")) {
            switch (outcomeUnit) {
                case "metr":
                    lengthPOJO = convertInchToMeter(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "stopa":
                    lengthPOJO = convertMeterToFoot(convertInchToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "jard":
                    lengthPOJO = convertMeterToYard(convertInchToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila":
                    lengthPOJO = convertMeterToMile(convertInchToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila morska":
                    lengthPOJO = convertMeterToNauticalMile(convertInchToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
            }
        } else if (incomeUnit.equals("stopa")) {
            switch (outcomeUnit) {
                case "metr":
                    lengthPOJO = convertFootToMeter(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "cal":
                    lengthPOJO = convertMeterToInch(convertFootToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "jard":
                    lengthPOJO = convertMeterToYard(convertFootToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila":
                    lengthPOJO = convertMeterToMile(convertFootToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila morska":
                    lengthPOJO = convertMeterToNauticalMile(convertFootToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
            }
        } else if (incomeUnit.equals("jard")) {
            switch (outcomeUnit) {
                case "metr":
                    lengthPOJO = convertYardToMeter(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "cal":
                    lengthPOJO = convertMeterToInch(convertYardToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "stopa":
                    lengthPOJO = convertMeterToFoot(convertYardToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila":
                    lengthPOJO = convertMeterToMile(convertYardToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila morska":
                    lengthPOJO = convertMeterToNauticalMile(convertYardToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
            }
        } else if (incomeUnit.equals("mila")) {
            switch (outcomeUnit) {
                case "metr":
                    lengthPOJO = convertMileToMeter(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "cal":
                    lengthPOJO = convertMeterToInch(convertMileToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "stopa":
                    lengthPOJO = convertMeterToFoot(convertMileToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "jard":
                    lengthPOJO = convertMeterToYard(convertMileToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila morska":
                    lengthPOJO = convertMeterToNauticalMile(convertMileToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
            }
        } else if (incomeUnit.equals("mila morska")) {
            switch (outcomeUnit) {
                case "metr":
                    lengthPOJO = convertNauticalMileToMeter(new BasicPOJO(value));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "cal":
                    lengthPOJO = convertMeterToInch(convertNauticalMileToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "stopa":
                    lengthPOJO = convertMeterToFoot(convertNauticalMileToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "mila":
                    lengthPOJO = convertMeterToMile(convertNauticalMileToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
                case "jard":
                    lengthPOJO = convertMeterToYard(convertNauticalMileToMeter(new BasicPOJO(value)));
                    model.addAttribute("lengthResult", lengthPOJO);
                    break;
            }
        }
    }
}
