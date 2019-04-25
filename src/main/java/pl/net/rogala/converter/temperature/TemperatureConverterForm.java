package pl.net.rogala.converter.temperature;

import lombok.Data;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Data
public class TemperatureConverterForm {
    @NumberFormat
    private String value;

    private String incomeUnit;
    private String outcomeUnit;
}
