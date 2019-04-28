package pl.net.rogala.converter.area;

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
public class AreaConverterController {

    private AreaConverterService areaConverterService;

    @Autowired
    public AreaConverterController(AreaConverterService areaConverterService) {
        this.areaConverterService = areaConverterService;
    }

    @ModelAttribute("areaRadioAllValues")
    public String[] getAreaRadioAllValues() {
        return new String[]{
                "metr kwadratowy", "ar", "hektar", "centymetr kwadratowy", "milimetr kwadratowy"
        };
    }

    @GetMapping("/area")
    public String showAreaConverterForm(Model model) {
        model.addAttribute("areaForm", new ConverterForm());
        return "temperature/areaConvForm";
    }

    @PostMapping("/area")
    public String handleAreaConverterForm(
            @ModelAttribute("areaForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/areaConvForm";
        } else {
            areaConverterService.convertArea(converterForm, model);

            return "temperature/areaResult";
        }
    }
}
