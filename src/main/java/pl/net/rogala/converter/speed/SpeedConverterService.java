package pl.net.rogala.converter.speed;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class SpeedConverterService {

    public BasicPOJO convertMPerSecToKmPerHour(BasicPOJO mpersecSpeed) {
        return new BasicPOJO(mpersecSpeed.getValue() * 3.6d);
    }

    public BasicPOJO convertKmPerHourToMPerSec(BasicPOJO kmperhourSpeed) {
        return new BasicPOJO(kmperhourSpeed.getValue() * 10d / 36d);
    }

    public BasicPOJO convertMilePerHourToKmPerHour(BasicPOJO mphpeed) {
        return new BasicPOJO(mphpeed.getValue() * 1.609344d);
    }

    public BasicPOJO convertKmPerHourToMilePerHour(BasicPOJO kmperhourSpeed) {
        return new BasicPOJO(kmperhourSpeed.getValue() / 1.609344d);
    }

    public void convertSpeed(@Valid @ModelAttribute("speedForm") ConverterForm converterForm, Model model) {
        BasicPOJO speedPOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            speedPOJO = new BasicPOJO(value);
            model.addAttribute("speedResult", speedPOJO);
        } else if (incomeUnit.equals("m/s")) {
            switch (outcomeUnit) {
                case "km/h":
                    speedPOJO = convertMPerSecToKmPerHour(new BasicPOJO(value));
                    model.addAttribute("speedResult", speedPOJO);
                    break;
                case "mph":
                    speedPOJO = convertKmPerHourToMilePerHour(convertMPerSecToKmPerHour(new BasicPOJO(value)));
                    model.addAttribute("speedResult", speedPOJO);
                    break;
            }
        } else if (incomeUnit.equals("km/h")) {
            switch (outcomeUnit) {
                case "m/s":
                    speedPOJO = convertKmPerHourToMPerSec(new BasicPOJO(value));
                    model.addAttribute("speedResult", speedPOJO);
                    break;
                case "mph":
                    speedPOJO = convertKmPerHourToMilePerHour(new BasicPOJO(value));
                    model.addAttribute("speedResult", speedPOJO);
                    break;
            }
        } else if (incomeUnit.equals("mph")) {
            switch (outcomeUnit) {
                case "km/h":
                    speedPOJO = convertMilePerHourToKmPerHour(new BasicPOJO(value));
                    model.addAttribute("speedResult", speedPOJO);
                    break;
                case "m/s":
                    speedPOJO = convertKmPerHourToMPerSec(convertMilePerHourToKmPerHour(new BasicPOJO(value)));
                    model.addAttribute("speedResult", speedPOJO);
                    break;
            }
        }
    }
}
