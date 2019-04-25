package pl.net.rogala.converter.temperature;

import org.springframework.stereotype.Service;

@Service
public class TemperatureConverterService {

    public TemperaturePOJO convertCelciusToKelvin(TemperaturePOJO temperatureCelcius){
        return new TemperaturePOJO(temperatureCelcius.getValue()+273.15);
    }

    public TemperaturePOJO convertKelvinToCelsius(TemperaturePOJO temperatureKelvin){
       return new TemperaturePOJO(temperatureKelvin.getValue()-273.15);
    }

    public TemperaturePOJO convertCelsiusToFahrenheit(TemperaturePOJO temperatureCelsius){
        return new TemperaturePOJO((temperatureCelsius.getValue()*1.8)+32);
    }

    public TemperaturePOJO convertFahrenheitToCelsius(TemperaturePOJO temperatureFahrenheit){
        return new TemperaturePOJO((temperatureFahrenheit.getValue()-32)/1.8);
    }

    public TemperaturePOJO convertKelvinToFahrenheit(TemperaturePOJO temperatureKelvin){
        return new TemperaturePOJO((temperatureKelvin.getValue()*1.8)-459.67);
    }

    public TemperaturePOJO convertFahrenheitToKelvin(TemperaturePOJO temperatureFahrenheit){
        return new TemperaturePOJO((temperatureFahrenheit.getValue()+459.67)*5/9);
    }


}
