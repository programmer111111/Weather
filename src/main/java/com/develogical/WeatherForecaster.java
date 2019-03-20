package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

public interface WeatherForecaster {
    public Forecast getForecast(Region city, Day day);
}
