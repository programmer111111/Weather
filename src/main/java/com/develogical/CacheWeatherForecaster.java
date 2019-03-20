package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import java.util.HashMap;

public class CacheWeatherForecaster implements WeatherForecaster {
    WeatherForecaster forecaster;
    HashMap<String, Forecast> cache;
    int cacheSize;

    public CacheWeatherForecaster(WeatherForecaster forecaster, int cacheSize) {
        this.forecaster = forecaster;
        this.cache = new HashMap<String, Forecast>();
        this.cacheSize = cacheSize;
    }

    public Forecast getForecast(Region city, Day day) {
        String key = city.toString() + day.toString();

        if (this.cache.containsKey(key)) {
            return this.cache.get(key);
        } else {
            Forecast forecast = this.forecaster.getForecast(city, day);

            int size = cache.size();
            if(size == cacheSize) {
                cache.clear();
            }

            this.cache.put(key, forecast);
            return forecast;
        }
    }

}
