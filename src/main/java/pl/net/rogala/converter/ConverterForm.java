package pl.net.rogala.converter;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConverterForm {
    @NumberFormat
    private String value;

    private String incomeUnit;
    private String outcomeUnit;
}
