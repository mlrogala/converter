package pl.net.rogala.converter.area;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class AreaConverterService {

    public BasicPOJO convertCubicMeterToCubicCm(BasicPOJO cubicMeterArea) {
        return new BasicPOJO(cubicMeterArea.getValue() * 10000d);
    }

    public BasicPOJO convertCubicCmToCubicMeter(BasicPOJO cubicCmArea) {
        return new BasicPOJO(cubicCmArea.getValue() / 10000d);
    }

    public BasicPOJO convertCubicCmToCubicMm(BasicPOJO cubicCmArea) {
        return new BasicPOJO(cubicCmArea.getValue() * 100d);
    }

    public BasicPOJO convertCubicMmToCubicCm(BasicPOJO cubicMmArea) {
        return new BasicPOJO(cubicMmArea.getValue() / 100d);
    }

    public BasicPOJO convertCubicMeterToCubicMm(BasicPOJO cubicMeterArea) {
        return new BasicPOJO(cubicMeterArea.getValue() * 1000000d);
    }

    public BasicPOJO convertCubicMmToCubicMeter(BasicPOJO cubicMmArea) {
        return new BasicPOJO(cubicMmArea.getValue() / 1000000d);
    }

    public BasicPOJO convertCubicMeterToAre(BasicPOJO cubicMeterArea) {
        return new BasicPOJO(cubicMeterArea.getValue() / 100d);
    }

    public BasicPOJO convertAreToCubicMeter(BasicPOJO areArea) {
        return new BasicPOJO(areArea.getValue() * 100d);
    }

    public BasicPOJO convertCubicMeterToHectare(BasicPOJO cubicMeterArea) {
        return new BasicPOJO(cubicMeterArea.getValue() / 10000d);
    }

    public BasicPOJO convertHectareToCubicMeter(BasicPOJO hectareArea) {
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
                    areaPOJO = convertCubicMeterToCubicCm(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "milimetr kwadratowy":
                    areaPOJO = convertCubicMeterToCubicMm(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "ar":
                    areaPOJO = convertCubicMeterToAre(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "hektar":
                    areaPOJO = convertCubicMeterToHectare(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        } else if (incomeUnit.equals("centymetr kwadratowy")) {
            switch (outcomeUnit) {
                case "metr kwadratowy":
                    areaPOJO = convertCubicCmToCubicMeter(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "milimetr kwadratowy":
                    areaPOJO = convertCubicCmToCubicMm(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "ar":
                    areaPOJO = convertCubicMeterToAre(convertCubicCmToCubicMeter(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "hektar":
                    areaPOJO = convertCubicCmToCubicMeter(convertCubicMeterToHectare(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        } else if (incomeUnit.equals("milimetr kwadratowy")) {
            switch (outcomeUnit) {
                case "metr kwadratowy":
                    areaPOJO = convertCubicMmToCubicMeter(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "centymetr kwadratowy":
                    areaPOJO = convertCubicMmToCubicCm(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "ar":
                    areaPOJO = convertCubicMmToCubicMeter(convertCubicMeterToAre(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "hektar":
                    areaPOJO = convertCubicMmToCubicMeter(convertCubicMeterToHectare(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        } else if (incomeUnit.equals("ar")) {
            switch (outcomeUnit) {
                case "centymetr kwadratowy":
                    areaPOJO = convertAreToCubicMeter(convertCubicMeterToCubicCm(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "milimetr kwadratowy":
                    areaPOJO = convertAreToCubicMeter(convertCubicMeterToCubicMm(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "metr":
                    areaPOJO = convertAreToCubicMeter(new BasicPOJO(value));
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
                    areaPOJO = convertHectareToCubicMeter(convertCubicMeterToCubicCm(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "milimetr kwadratowy":
                    areaPOJO = convertHectareToCubicMeter(convertCubicMeterToCubicMm(new BasicPOJO(value)));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "ar":
                    areaPOJO = convertHectareToAre(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
                case "metr":
                    areaPOJO = convertHectareToCubicMeter(new BasicPOJO(value));
                    model.addAttribute("areaResult", areaPOJO);
                    break;
            }
        }
    }
}
