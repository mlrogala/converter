package pl.net.rogala.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasicPOJO {
    private double value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
