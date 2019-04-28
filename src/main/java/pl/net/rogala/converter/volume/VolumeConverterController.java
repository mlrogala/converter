package pl.net.rogala.converter.volume;

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
public class VolumeConverterController {

    private VolumeConverterService volumeConverterService;

    @Autowired
    public VolumeConverterController(VolumeConverterService volumeConverterService) {
        this.volumeConverterService = volumeConverterService;
    }

    @ModelAttribute("volumeSingleSelectAllValues")
    public String[] getSingleSelectAllValues() {
        return new String[]{
                "metr sześcienny", "decymetr sześcienny", "centymetr sześcienny", "milimetr sześcienny"
        };
    }

    @GetMapping("/volume")
    public String showVolumeConverterForm(Model model) {
        model.addAttribute("volumeForm", new ConverterForm());
        return "temperature/volumeConvForm";
    }

    @PostMapping("/volume")
    public String handleVolumeConverterForm(
            @ModelAttribute("volumeForm") @Valid ConverterForm converterForm,
            Model model,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "temperature/volumeConvForm";
        } else {
            volumeConverterService.convertVolume(converterForm, model);
            return "temperature/volumeResult";
        }
    }


}
