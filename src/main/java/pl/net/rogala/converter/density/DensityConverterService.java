package pl.net.rogala.converter.density;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class DensityConverterService {

    public BasicPOJO convertGPerCubicCmToKgPerCubicMeter(BasicPOJO gperCubicCmDensity){
        return new BasicPOJO(gperCubicCmDensity.getValue()*1000d);
    }

    public BasicPOJO convertKgPerCubicMeterToGPerCubicCm(BasicPOJO kgperCubicMeterDensity){
        return new BasicPOJO(kgperCubicMeterDensity.getValue()/1000d);
    }

    public void convertDensity(@Valid @ModelAttribute("volumeForm") ConverterForm converterForm, Model model) {
        BasicPOJO densityPOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            densityPOJO = new BasicPOJO(value);
            model.addAttribute("densityResult", densityPOJO);
        } else if (incomeUnit.equals("g/cm3") && outcomeUnit.equals("kg/m3")) {
            densityPOJO = convertGPerCubicCmToKgPerCubicMeter(new BasicPOJO(value));
            model.addAttribute("densityResult", densityPOJO);
        } else if (incomeUnit.equals("kg/m3") && outcomeUnit.equals("g/cm3")) {
            densityPOJO = convertKgPerCubicMeterToGPerCubicCm(new BasicPOJO(value));
            model.addAttribute("densityResult", densityPOJO);
        }
    }
}
