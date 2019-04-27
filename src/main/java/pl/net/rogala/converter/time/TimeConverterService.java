package pl.net.rogala.converter.time;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.net.rogala.converter.BasicPOJO;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Service
public class TimeConverterService {

    public BasicPOJO convertYearToDay(BasicPOJO yearTime){
        return new BasicPOJO(yearTime.getValue()*365.25d);
    }

    public BasicPOJO convertDayToYear(BasicPOJO dayTime){
        return new BasicPOJO(dayTime.getValue()/365.25d);
    }

    public BasicPOJO convertDayToHour(BasicPOJO dayTime) {
        return new BasicPOJO(dayTime.getValue() * 24d);
    }

    public BasicPOJO convertHourToDay(BasicPOJO hourTime){
        return new BasicPOJO(hourTime.getValue() / 24d);
    }

    public BasicPOJO convertHourToMinute(BasicPOJO hourTime){
        return new BasicPOJO(hourTime.getValue()*60d);
    }

    public BasicPOJO convertMinuteToHour(BasicPOJO minuteTime){
        return new BasicPOJO(minuteTime.getValue()/60d);
    }

    public BasicPOJO convertMinuteToSecond(BasicPOJO minuteTime){
        return new BasicPOJO(minuteTime.getValue()*60d);
    }

    public BasicPOJO convertSecondToMinute(BasicPOJO secondTime){
        return new BasicPOJO(secondTime.getValue()/60d);
    }

    public void convertTime(@Valid @ModelAttribute("timeForm") ConverterForm converterForm, Model model) {
        BasicPOJO timePOJO;
        String incomeUnit = converterForm.getIncomeUnit();
        String outcomeUnit = converterForm.getOutcomeUnit();
        double value = Double.valueOf(converterForm.getValue());
        if (incomeUnit.equals(outcomeUnit)) {
            timePOJO = new BasicPOJO(value);
            model.addAttribute("timeResult", timePOJO);
        } else if (incomeUnit.equals("sekundy")) {
            switch (outcomeUnit) {
                case "minuty":
                    timePOJO = convertSecondToMinute(new BasicPOJO(value));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "godziny":
                    timePOJO = convertMinuteToHour(convertSecondToMinute(new BasicPOJO(value)));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "doby":
                    timePOJO = convertHourToDay(convertMinuteToHour(convertSecondToMinute(new BasicPOJO(value))));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "lata":
                    timePOJO = convertDayToYear(convertHourToDay(convertMinuteToHour(convertSecondToMinute(new BasicPOJO(value)))));
                    model.addAttribute("timeResult", timePOJO);
                    break;
            }
        } else if (incomeUnit.equals("minuty")) {
            switch (outcomeUnit) {
                case "sekundy":
                    timePOJO = convertMinuteToSecond(new BasicPOJO(value));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "godziny":
                    timePOJO = convertMinuteToHour(new BasicPOJO(value));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "doby":
                    timePOJO = convertHourToDay(convertMinuteToHour(new BasicPOJO(value)));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "lata":
                    timePOJO = convertDayToYear(convertHourToDay(convertMinuteToHour(new BasicPOJO(value))));
                    model.addAttribute("timeResult", timePOJO);
                    break;
            }
        } else if (incomeUnit.equals("godziny")) {
            switch (outcomeUnit) {
                case "minuty":
                    timePOJO = convertHourToMinute(new BasicPOJO(value));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "sekundy":
                    timePOJO = convertMinuteToSecond(convertHourToMinute(new BasicPOJO(value)));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "doby":
                    timePOJO = convertHourToDay(new BasicPOJO(value));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "lata":
                    timePOJO = convertDayToYear(convertHourToDay(new BasicPOJO(value)));
                    model.addAttribute("timeResult", timePOJO);
                    break;
            }
        } else if (incomeUnit.equals("doby")) {
            switch (outcomeUnit) {
                case "minuty":
                    timePOJO = convertHourToMinute(convertDayToHour(new BasicPOJO(value)));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "godziny":
                    timePOJO = convertDayToHour(new BasicPOJO(value));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "sekundy":
                    timePOJO = convertMinuteToSecond(convertHourToMinute(convertDayToHour(new BasicPOJO(value))));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "lata":
                    timePOJO = convertDayToYear(new BasicPOJO(value));
                    model.addAttribute("timeResult", timePOJO);
                    break;
            }
        } else if (incomeUnit.equals("lata")) {
            switch (outcomeUnit) {
                case "sekundy":
                    timePOJO = convertMinuteToSecond(convertHourToMinute(convertDayToHour(convertYearToDay(new BasicPOJO(value)))));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "minuty":
                    timePOJO = convertHourToMinute(convertDayToHour(convertYearToDay(new BasicPOJO(value))));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "godziny":
                    timePOJO = convertDayToHour(convertYearToDay(new BasicPOJO(value)));
                    model.addAttribute("timeResult", timePOJO);
                    break;
                case "doby":
                    timePOJO = convertYearToDay(new BasicPOJO(value));
                    model.addAttribute("timeResult", timePOJO);
                    break;
            }
        }
    }
}
