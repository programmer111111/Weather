package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class WeatherForecastAdaptor implements WeatherForecaster{

    public Forecaster forecaster;

    WeatherForecastAdaptor(){
        forecaster = new Forecaster();
    }

    public Forecast getForecast(Region city, Day day){
        return forecaster.forecastFor(city, day);
    }
}
