package pl.net.rogala.converter.density;

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
public class DensityConverterController {

    private DensityConverterService densityConverterService;

    @Autowired
    public DensityConverterController(DensityConverterService densityConverterService) {
        this.densityConverterService = densityConverterService;
    }

    @ModelAttribute("densitySingleSelectAllValues")
    public String[] getDensitySingleSelectAllValues() {
        return new String[]{
                "g/cm3", "kg/m3"
        };
    }

    @GetMapping("/density")
    public String showDensityConverterForm(Model model) {
        model.addAttribute("densityForm", new ConverterForm());
        return "temperature/densityConvForm";
    }

    @PostMapping("/density")
    public String handleDensityConverterForm(
            @ModelAttribute("densityForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/densityConvForm";
        } else {
            densityConverterService.convertDensity(converterForm, model);
            return "temperature/densityResult";
        }
    }
}
