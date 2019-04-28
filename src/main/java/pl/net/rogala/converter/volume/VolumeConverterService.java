package pl.net.rogala.converter.volume;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class VolumeConverterService {

    public BasicPOJO convertCubicMeterToCubicDecymeter(BasicPOJO cubicMeterVolume){
        return new BasicPOJO(cubicMeterVolume.getValue()*1000d);
    }

    public BasicPOJO convertCubicDecymeterToCubicMeter(BasicPOJO cubicDecymeterVolume){
        return new BasicPOJO(cubicDecymeterVolume.getValue()/1000d);
    }

    public BasicPOJO convertCubicMeterToCubicCentymeter(BasicPOJO cubicMeterVolume){
        return new BasicPOJO(cubicMeterVolume.getValue()*1000000d);
    }

    public BasicPOJO convertCubicCentymeterToCubicMeter(BasicPOJO cubicCentymeterVolume){
        return new BasicPOJO(cubicCentymeterVolume.getValue()/1000000d);
    }

    public BasicPOJO convertCubicMeterToCubicMilimeter(BasicPOJO cubicMeterVolume){
        return new BasicPOJO(cubicMeterVolume.getValue()*1000000000d);
    }

    public BasicPOJO convertCubicMilimeterToCubicMeter(BasicPOJO cubicMilimeterVolume){
        return new BasicPOJO(cubicMilimeterVolume.getValue()/1000000000d);
    }

    public void convertVolume(@Valid @ModelAttribute("volumeForm") ConverterForm converterForm, Model model) {
        BasicPOJO volumePOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            volumePOJO = new BasicPOJO(value);
            model.addAttribute("volumeResult", volumePOJO);
        } else if (incomeUnit.equals("metr sześcienny")) {
            switch (outcomeUnit) {
                case "decymetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicDecymeter(new BasicPOJO(value));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
                case "centymetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicCentymeter(new BasicPOJO(value));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
                case "milimetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicMilimeter(new BasicPOJO(value));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
            }
        } else if (incomeUnit.equals("decymetr sześcienny")) {
            switch (outcomeUnit) {
                case "metr sześcienny":
                    volumePOJO = convertCubicDecymeterToCubicMeter(new BasicPOJO(value));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
                case "centymetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicCentymeter(convertCubicDecymeterToCubicMeter(new BasicPOJO(value)));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
                case "milimetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicMilimeter(convertCubicDecymeterToCubicMeter(new BasicPOJO(value)));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
            }
        } else if (incomeUnit.equals("centymetr sześcienny")) {
            switch (outcomeUnit) {
                case "decymetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicDecymeter(convertCubicCentymeterToCubicMeter(new BasicPOJO(value)));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
                case "metr sześcienny":
                    volumePOJO = convertCubicCentymeterToCubicMeter(new BasicPOJO(value));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
                case "milimetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicMilimeter(convertCubicCentymeterToCubicMeter(new BasicPOJO(value)));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
            }
        } else if (incomeUnit.equals("milimetr sześcienny")) {
            switch (outcomeUnit) {
                case "decymetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicDecymeter(convertCubicMilimeterToCubicMeter(new BasicPOJO(value)));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
                case "centymetr sześcienny":
                    volumePOJO = convertCubicMeterToCubicCentymeter(convertCubicMilimeterToCubicMeter(new BasicPOJO(value)));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
                case "metr sześcienny":
                    volumePOJO = convertCubicMilimeterToCubicMeter(new BasicPOJO(value));
                    model.addAttribute("volumeResult", volumePOJO);
                    break;
            }
        }
    }
}
