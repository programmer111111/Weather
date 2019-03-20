package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class WeatherForecastAdaptorTest {

    @Test
    public void returnsForecastForLondonOnMonday() throws Exception {
        WeatherForecastAdaptor adaptor = new WeatherForecastAdaptor();
        Forecast forcast = ((WeatherForecastAdaptor) adaptor).getForecast(Region.LONDON, Day.MONDAY);
        assertThat(forcast.summary(), equalTo("foggy"));
        assertThat(forcast.temperature(), equalTo(10));
    }
}
