package pl.net.rogala.converter.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.net.rogala.converter.ConverterForm;

import javax.validation.Valid;

@Controller
public class TimeConverterController {

    private TimeConverterService timeConverterService;

    @Autowired
    public TimeConverterController(TimeConverterService timeConverterService) {
        this.timeConverterService = timeConverterService;
    }

    @ModelAttribute("timeRadioAllValues")
    public String[] getTimeRadioAllValues() {
        return new String[]{
                "sekundy", "minuty", "godziny", "doby", "lata"
        };
    }

    @GetMapping("/time")
    public String showTimeConverterForm(Model model) {
        model.addAttribute("timeForm", new ConverterForm());
        return "temperature/timeConvForm";
    }

    @PostMapping("/time")
    public String handleTimeConverterForm(
            @ModelAttribute("timeForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/timeConvForm";
        } else {
            timeConverterService.convertTime(converterForm, model);

            return "temperature/timeResult";
        }
    }
}
