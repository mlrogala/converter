package pl.net.rogala.converter.area;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class AreaConverterService {

    public BasicPOJO convertSqrtMeterToSqrtCm(BasicPOJO sqrtMeterArea) {
        return new BasicPOJO(sqrtMeterArea.getValue() * 10000d);
    }

    public BasicPOJO convertSqrtCmToSqrtMeter(BasicPOJO sqrtCmArea) {
        return new BasicPOJO(sqrtCmArea.getValue() / 10000d);
    }

    public BasicPOJO convertSqrtCmToSqrtMm(BasicPOJO sqrtCmArea) {
        return new BasicPOJO(sqrtCmArea.getValue() * 100d);
    }

    public BasicPOJO convertSqrtMmToSqrtCm(BasicPOJO SqrtMmArea) {
        return new BasicPOJO(SqrtMmArea.getValue() / 100d);
    }

    public BasicPOJO convertSqrtMeterToSqrtMm(BasicPOJO sqrtMeterArea) {
        return new BasicPOJO(sqrtMeterArea.getValue() * 1000000d);
    }

    public BasicPOJO convertSqrtMmToSqrtMeter(BasicPOJO sqrtMmArea) {
        return new BasicPOJO(sqrtMmArea.getValue() / 1000000d);
    }

    public BasicPOJO convertSqrtMeterToAre(BasicPOJO sqrtMeterArea) {
        return new BasicPOJO(sqrtMeterArea.getValue() / 100d);
    }

    public BasicPOJO convertAreToSqrtMeter(BasicPOJO areArea) {
        return new BasicPOJO(areArea.getValue() * 100d);
    }

    public BasicPOJO convertSqrtMeterToHectare(BasicPOJO sqrtMeterArea) {
        return new BasicPOJO(sqrtMeterArea.getValue() / 10000d);
    }

    public BasicPOJO convertHectareToSqrtMeter(BasicPOJO hectareArea) {
        return new BasicPOJO(hectareArea.getValue() * 10000d);
    }

    public BasicPOJO convertHectareToAre(BasicPOJO hectareArea) {
        return new BasicPOJO(hectareArea.getValue() * 100d);
    }

    public BasicPOJO convertAreToHectare(BasicPOJO areArea) {
        return new BasicPOJO(areArea.getValue() / 100d);
    }

    public void convertArea(@Valid @ModelAttribute("areaForm") ConverterForm converterForm, Model model) {
        BasicPOJO areaPOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            areaPOJO = new BasicPOJO(value);
            model.addAttribute("areaResult", areaPOJO);
        } else if (incomeUnit.equals("metr kwadratowy")) {
            switch (outcomeUnit) {
                case "centymetr kwadratowy":
                    areaPOJO = convertSqrtMeterToSqrtCm(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "milimetr kwadratowy":
                    areaPOJO = convertSqrtMeterToSqrtMm(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "ar":
                    areaPOJO = convertSqrtMeterToAre(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "hektar":
                    areaPOJO = convertSqrtMeterToHectare(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        } else if (incomeUnit.equals("centymetr kwadratowy")) {
            switch (outcomeUnit) {
                case "metr kwadratowy":
                    areaPOJO = convertSqrtCmToSqrtMeter(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "milimetr kwadratowy":
                    areaPOJO = convertSqrtCmToSqrtMm(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "ar":
                    areaPOJO = convertSqrtMeterToAre(convertSqrtCmToSqrtMeter(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "hektar":
                    areaPOJO = convertSqrtCmToSqrtMeter(convertSqrtMeterToHectare(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        } else if (incomeUnit.equals("milimetr kwadratowy")) {
            switch (outcomeUnit) {
                case "metr kwadratowy":
                    areaPOJO = convertSqrtMmToSqrtMeter(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "centymetr kwadratowy":
                    areaPOJO = convertSqrtMmToSqrtCm(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "ar":
                    areaPOJO = convertSqrtMmToSqrtMeter(convertSqrtMeterToAre(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "hektar":
                    areaPOJO = convertSqrtMmToSqrtMeter(convertSqrtMeterToHectare(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        } else if (incomeUnit.equals("ar")) {
            switch (outcomeUnit) {
                case "centymetr kwadratowy":
                    areaPOJO = convertAreToSqrtMeter(convertSqrtMeterToSqrtCm(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "milimetr kwadratowy":
                    areaPOJO = convertAreToSqrtMeter(convertSqrtMeterToSqrtMm(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "metr":
                    areaPOJO = convertAreToSqrtMeter(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "hektar":
                    areaPOJO = convertAreToHectare(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        } else if (incomeUnit.equals("hektar")) {
            switch (outcomeUnit) {
                case "centymetr kwadratowy":
                    areaPOJO = convertHectareToSqrtMeter(convertSqrtMeterToSqrtCm(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "milimetr kwadratowy":
                    areaPOJO = convertHectareToSqrtMeter(convertSqrtMeterToSqrtMm(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "ar":
                    areaPOJO = convertHectareToAre(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "metr":
                    areaPOJO = convertHectareToSqrtMeter(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        }
    }
}
