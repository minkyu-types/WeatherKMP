package org.dosys.project.presentation.feature.weather.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.dosys.project.util.TimeConverter
import org.dosys.weather_domain.model.Location
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WeatherMainScreen(
    onWeatherClick: (Long) -> Unit,
) {
    val weather = CurrentWeatherModel(
        location = Location(33.44, -94.04),
        timezone = "America/Chicago",
        timezoneOffset = -18000,
        current = Current(
            dt = 1684929490,
            sunrise = 1684926645,
            sunset = 1684977332,
            temp = 292.55,
            feelsLike = 292.87,
            pressure = 1014,
            humidity = 89,
            dewPoint = 290.69,
            uvi = 0.16,
            clouds = 53,
            visibility = 10000,
            windSpeed = 3.13,
            windDeg = 93,
            windGust = 6.71,
            weather = emptyList()
        ),
    )

    Column(
        modifier = Modifier
    ) {
        WeatherMainInfoArea(
            weather.current.dt,
            weather.timezone,
            weather.timezoneOffset,
            weather.current
        )
    }
}

@Composable
private fun WeatherMainInfoArea(
    dateTime: Long,
    timezone: String,
    timezoneOffset: Int,
    current: Current,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = TimeConverter.getCurrentDateTimeFromUTC(
                dateTime, timezone, timezoneOffset
            ),
            color = Color.Red,
        )
        Text(
            text = timezone,
            color = Color.Red,
        )
        Text(
            text = "Feels like ${current.temp}°C. ${current.weather[0].main}, ${current.weather[0].description}",
            color = Color.Red,
        )
        VerticalDivider(
            modifier = Modifier
                .width(1.dp)
                .height(80.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Row(

            ) {
               Text(
                   text = "Wind ${current.windSpeed}m/s",
               )
               Text(
                   text = "Pressure ${current.pressure}hPa"
               )
            }
            Row(

            ) {
                Text(
                    text = "Humidity: ${current.humidity}%"
                )
                Text(
                    text = "Dew point: ${current.dewPoint}°C"
                )
            }
            Text(
                text = "Visibility: ${current.visibility}km"
            )
        }
    }
}

@Preview
@Composable
private fun WeatherMainScreenPreview(

) {
    WeatherMainScreen(
        onWeatherClick = {

        }
    )
}