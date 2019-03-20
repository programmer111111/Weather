package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CacheWeatherForecasterTest {
    @Test
    public void returnsForecastForLondonOnMonday() throws Exception {
        WeatherForecaster delegate = mock(WeatherForecaster.class);
        Forecast hot = new Forecast("hot", 77);

        when(delegate.getForecast(Region.LONDON, Day.MONDAY)).thenReturn(hot);

        CacheWeatherForecaster cache = new CacheWeatherForecaster(delegate, 100);

        Forecast forecast = cache.getForecast(Region.LONDON, Day.MONDAY);
        assertThat(forecast, equalTo(hot));

        forecast = cache.getForecast(Region.LONDON, Day.MONDAY);
        assertThat(forecast, equalTo(hot));

        verify(delegate, times(1)).getForecast(Region.LONDON, Day.MONDAY);
    }

    @Test
    public void usesTheCaches() {
        WeatherForecaster delegate = mock(WeatherForecaster.class);
        CacheWeatherForecaster cache = new CacheWeatherForecaster(delegate, 100);
        cache.getForecast(Region.LONDON, Day.MONDAY);
        cache.getForecast(Region.LONDON, Day.TUESDAY);
        cache.getForecast(Region.LONDON, Day.TUESDAY);

        verify(delegate, times(1)).getForecast(Region.LONDON, Day.MONDAY);
        verify(delegate, times(1)).getForecast(Region.LONDON, Day.TUESDAY);
    }

    @Test
    public void evictOldEntriesIfFull () {
        int cacheSize = 1;
        WeatherForecaster delegate = mock(WeatherForecaster.class);
        Forecast hot = new Forecast("hot", 77);
        Forecast cold = new Forecast("cold", 17);

        when(delegate.getForecast(Region.LONDON, Day.MONDAY)).thenReturn(hot);
        when(delegate.getForecast(Region.LONDON, Day.MONDAY)).thenReturn(cold);

        CacheWeatherForecaster cache = new CacheWeatherForecaster(delegate, cacheSize);

        cache.getForecast(Region.LONDON, Day.MONDAY);
        cache.getForecast(Region.EDINBURGH, Day.MONDAY);
        Forecast forecast = cache.getForecast(Region.LONDON, Day.MONDAY);

        assertThat(forecast, equalTo(cold));

        verify(delegate, times(2)).getForecast(Region.LONDON, Day.MONDAY);
        verify(delegate, times(1)).getForecast(Region.EDINBURGH, Day.MONDAY);
    }
}
