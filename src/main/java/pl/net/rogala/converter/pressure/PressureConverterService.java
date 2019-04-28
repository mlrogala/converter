package pl.net.rogala.converter.pressure;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class PressureConverterService {

    public BasicPOJO convertPaToHPa(BasicPOJO paPressure) {
        return new BasicPOJO(paPressure.getValue() / 100d);
    }

    public BasicPOJO convertHPaToPa(BasicPOJO hPaPressure) {
        return new BasicPOJO(hPaPressure.getValue() * 100d);
    }

    public BasicPOJO convertHPaToAtm(BasicPOJO hPaPressure) {
        return new BasicPOJO(hPaPressure.getValue() / 980.665d);
    }

    public BasicPOJO convertAtmToHPa(BasicPOJO atmPressure) {
        return new BasicPOJO(atmPressure.getValue() * 980.665d);
    }

    public BasicPOJO convertHPaToBar(BasicPOJO hPaPressure) {
        return new BasicPOJO(hPaPressure.getValue() / 100d);
    }

    public BasicPOJO convertBarToHPa(BasicPOJO barPressure) {
        return new BasicPOJO(barPressure.getValue() * 100d);
    }

    public BasicPOJO convertHPaToMmHg(BasicPOJO hPaPressure) {
        return new BasicPOJO(hPaPressure.getValue() / 1.333d);
    }

    public BasicPOJO convertmmHgToHPa(BasicPOJO mmHgPressure) {
        return new BasicPOJO(mmHgPressure.getValue() * 1.333d);
    }

    public void convertPressure(@Valid @ModelAttribute("pressureForm") ConverterForm converterForm, Model model) {
        BasicPOJO pressurePOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            pressurePOJO = new BasicPOJO(value);
            model.addAttribute("pressureResult", pressurePOJO);
        } else if (incomeUnit.equals("Pa")) {
            switch (outcomeUnit) {
                case "hPa":
                    pressurePOJO = convertPaToHPa(new BasicPOJO(value));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "atm":
                    pressurePOJO = convertHPaToAtm(convertPaToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "bar":
                    pressurePOJO = convertHPaToBar(convertPaToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "mmHg":
                    pressurePOJO = convertHPaToMmHg(convertPaToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
            }
        } else if (incomeUnit.equals("hPa")) {
            switch (outcomeUnit) {
                case "Pa":
                    pressurePOJO = convertHPaToPa(new BasicPOJO(value));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "atm":
                    pressurePOJO = convertHPaToAtm(new BasicPOJO(value));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "bar":
                    pressurePOJO = convertHPaToBar(new BasicPOJO(value));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "mmHg":
                    pressurePOJO = convertHPaToMmHg(new BasicPOJO(value));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
            }
        } else if (incomeUnit.equals("atm")) {
            switch (outcomeUnit) {
                case "hPa":
                    pressurePOJO = convertAtmToHPa(new BasicPOJO(value));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "Pa":
                    pressurePOJO = convertHPaToPa(convertAtmToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "bar":
                    pressurePOJO = convertHPaToBar(convertAtmToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "mmHg":
                    pressurePOJO = convertHPaToMmHg(convertAtmToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
            }
        } else if (incomeUnit.equals("bar")) {
            switch (outcomeUnit) {
                case "hPa":
                    pressurePOJO = convertBarToHPa(new BasicPOJO(value));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "atm":
                    pressurePOJO = convertHPaToAtm(convertBarToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "Pa":
                    pressurePOJO = convertHPaToPa(convertBarToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "mmHg":
                    pressurePOJO = convertHPaToMmHg(convertBarToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
            }
        } else if (incomeUnit.equals("mmHg")) {
            switch (outcomeUnit) {
                case "hPa":
                    pressurePOJO = convertmmHgToHPa(new BasicPOJO(value));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "atm":
                    pressurePOJO = convertHPaToAtm(convertmmHgToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "bar":
                    pressurePOJO = convertHPaToBar(convertmmHgToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
                case "Pa":
                    pressurePOJO = convertHPaToPa(convertmmHgToHPa(new BasicPOJO(value)));
                    model.addAttribute("pressureResult", pressurePOJO);
                    break;
            }
        }
    }
}
