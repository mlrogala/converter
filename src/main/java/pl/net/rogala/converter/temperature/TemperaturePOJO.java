package pl.net.rogala.converter.temperature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TemperaturePOJO {
    private double value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
