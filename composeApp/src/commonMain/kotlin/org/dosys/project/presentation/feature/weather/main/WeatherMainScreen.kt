package org.dosys.project.presentation.feature.weather.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.ShieldMoon
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.WbCloudy
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material.icons.rounded.WindPower
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import com.samsung.weather_data.remote.model.type.Language
import com.samsung.weather_data.remote.model.type.WeatherUnit
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toLocalDateTime
import org.dosys.project.presentation.component.GlassPanel
import org.dosys.project.presentation.core.Rain_Text_SkyBlue
import org.dosys.project.presentation.core.Temp_Text_min
import org.dosys.project.presentation.feature.base.LoadState
import org.dosys.project.util.DayNameStyle
import org.dosys.project.util.ImageConverter
import org.dosys.project.util.TemperatureConverter
import org.dosys.project.util.TimeConverter
import org.dosys.project.util.dayOfWeekKo
import org.dosys.project.util.getWindDirectionText
import org.dosys.project.util.withComma
import org.koin.mp.KoinPlatform.getKoin
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

private const val DAY_SEC = 86_400L

@OptIn(ExperimentalTime::class)
@Composable
fun WeatherMainScreen(
    onWeatherClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: WeatherMainViewModel = getKoin().get<WeatherMainViewModel>()
    val currentWeatherState by viewModel.state.collectAsState(null)

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is WeatherMainSideEffect.ShowAlert -> {

                }
                is WeatherMainSideEffect.ChangeListType -> {

                }
            }
        }
    }

    when (currentWeatherState?.loadState) {
        LoadState.Idle -> {
            Logger.d { "WeatherMainScreen: LoadState.Idle" }
        }
        LoadState.Loading -> {
            Logger.d { "WeatherMainScreen: LoadState.Loading" }
        }
        LoadState.Success -> {
            val currentWeatherModel = currentWeatherState?.weather!!
                .also {
                    Logger.d(
                        messageString = it.toString()
                    )
                }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .verticalScroll(rememberScrollState())
            ) {
                WeatherMainInfoArea(
                    timezone = currentWeatherModel.timezone,
                    currentWeather = currentWeatherModel,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
//                WeatherHourlyInfoArea(
//                    currentTime = Clock.System.now().toLocalDateTime(TimeZone.of(weatherModel.timezone)),
//                    zone = weatherModel.timezone,
//                    zoneOffset = weatherModel.timezoneOffset,
//                    summary = currentWeather.summary ?: "No descriptions",
//                    hourly = weatherModel.hourly,
//                    modifier = Modifier
//                        .padding(horizontal = 32.dp)
//                )
                Spacer(modifier = Modifier.height(16.dp))
                WeatherDailyInfoArea(
                    weather = currentWeatherModel,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                WeatherDailySunWindInfo(
                    timezone = currentWeatherModel.timezone,
                    sunrise = currentWeatherModel.sys.sunrise,
                    sunset = currentWeatherModel.sys.sunset,
                    wind = currentWeatherModel.wind,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                WeatherFeelsLikeRainInfo(
                    currentWeather = currentWeatherModel,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                WeatherHumidityPressureInfo(
                    humidity = currentWeatherModel.main.humidity,
                    pressure = currentWeatherModel.main.pressure,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        else -> {
            Logger.d { "WeatherMainScreen: LoadState.Error" }
        }
    }
}

@Composable
private fun WeatherMainInfoArea(
    timezone: Int,
    currentWeather: CurrentWeatherModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "나의 위치",
            color = Color.White,
            fontSize = 24.sp
        )
        Text(
            text = "${currentWeather.sys.country}/${currentWeather.name}",
            color = Color.White,
            fontSize = 24.sp
        )
        Text(
            text = "${currentWeather.main.temp.toInt()}°C",
            color = Color.White,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = currentWeather.weather.first().main,
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = "최고:${currentWeather.main.tempMax.toInt()}°  최저:${currentWeather.main.tempMin.toInt()}°",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun WeatherDailyInfoArea(
    weather: CurrentWeatherModel,
    modifier: Modifier = Modifier
) {
    GlassPanel(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.CalendarMonth,
                tint = Color.White,
                contentDescription = null,
            )
            Text(
                text = "7일간의 일기예보",
                color = Color.White,
                modifier = modifier
                    .padding(top = 12.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)
            )
        }
//        weather.daily.forEach { day ->
//            DailyWeatherItem(
//                currTemp = weather.current.temp,
//                daily = day,
//                timezone = weather.timezone,
//                timezoneOffset = weather.timezoneOffset,
//                modifier = Modifier
//                    .padding(vertical = 12.dp)
//                    .fillMaxWidth()
//            )
//            if (day != weather.daily.last()) {
//                HorizontalDivider(
//                    modifier = Modifier
//                        .padding(horizontal = 16.dp)
//                        .fillMaxWidth()
//                        .height(1.dp)
//                        .background(color = Color.LightGray)
//                )
//            }
//        }
    }
}

//@Composable
//private fun WeatherHourlyInfoArea(
//    currentTime: LocalDateTime,
//    zone: String,
//    zoneOffset: Int,
//    summary: String,
//    hourly: List<HourlyModel>,
//    modifier: Modifier = Modifier
//) {
//    val hourlyHeader = hourly.first()
////        .first {
////            TimeConverter.getCurrentLocalDateTime(it.dt, zone, zoneOffset) == Clock.System.now()
////                .toLocalDateTime(TimeZone.of(zone))
////        }
//    val headerPosition = hourly.indexOf(hourlyHeader)
//    val hourlyWeathers = hourly.drop(headerPosition + 1)
//
//    GlassPanel(
//        modifier = modifier
//            .fillMaxWidth()
//    ) {
//        Text(
//            text = summary,
//            color = Color.White,
//            modifier = Modifier
//                .wrapContentWidth()
//                .padding(top = 12.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)
//        )
//        HorizontalDivider(Modifier.height(1.dp))
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(6.dp),
//            modifier = Modifier
//                .padding(top = 16.dp, bottom = 16.dp, start = 20.dp, end = 20.dp)
//        ) {
//            item(key = hourlyHeader.dt) {
//                HourlyWeatherHeaderItem(hourWeather = hourlyHeader)
//            }
//            items(
//                items = hourlyWeathers,
//                key = { hourlyWeather ->
//                    hourlyWeather.dt
//                },
//            ) { hourWeather ->
////                HourlyWeatherBodyItem(
////                    hourWeather = hourWeather,
////                    zone = zone,
////                    zoneOffset = zoneOffset
////                )
//            }
//        }
//    }
//}

@Composable
private fun HourlyWeatherHeaderItem(
    weather: CurrentWeatherModel,
    modifier: Modifier = Modifier
) {
    val weatherIcon = weather.weather.first().icon
    val celsiusTemp = TemperatureConverter.convert(
        weather.main.temp,
        WeatherUnit.TemperatureUnit.KELVIN,
        WeatherUnit.TemperatureUnit.CELSIUS
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
    ) {
        Text(
            text = "지금",
            color = Color.White
        )
        Spacer(Modifier.height(8.dp))
        AsyncImage(
            model = ImageConverter.openWeatherIconUrl(weatherIcon),
            contentDescription = null
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "${celsiusTemp.toInt()}",
            color = Color.White
        )
    }
}

//@OptIn(ExperimentalTime::class)
//@Composable
//private fun HourlyWeatherBodyItem(
//    weather: CurrentWeatherModel,
//    zone: String,
//    zoneOffset: Int,
//    modifier: Modifier = Modifier
//) {
//    val weatherIcon = weather.weather.first().icon
//    val currentTime = Clock.System.now().toLocalDateTime(TimeZone.of(zone)).hour
//    val itemTime = TimeConverter.convertUTCToLocalDateTime(hourWeather.dt, zone, zoneOffset)
//    val displayHour = if (currentTime + 1 == itemTime.hour) {
//        "${itemTime.hour}:${itemTime.minute}"
//    } else {
//        "${itemTime.hour}시"
//    }
//    val celsiusTemp = TemperatureConverter.convert(
//        hourWeather.temp,
//        WeatherUnit.TemperatureUnit.KELVIN,
//        WeatherUnit.TemperatureUnit.CELSIUS
//    )
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .padding(horizontal = 4.dp, vertical = 8.dp)
//    ) {
//        Text(
//            text = displayHour,
//            color = Color.White
//        )
//        Spacer(Modifier.height(8.dp))
//        AsyncImage(
//            model = ImageConverter.openWeatherIconUrl(weatherIcon),
//            contentDescription = null
//        )
//        Spacer(Modifier.height(8.dp))
//        Text(
//            text = "${celsiusTemp.toInt()}",
//            color = Color.White
//        )
//    }
//}

@OptIn(ExperimentalTime::class)
@Composable
private fun DailyWeatherItem(
    weather: CurrentWeatherModel,
    modifier: Modifier = Modifier
) {
    val tempData =
        weather.main.temp.convert(WeatherUnit.TemperatureUnit.KELVIN, WeatherUnit.TemperatureUnit.CELSIUS)
    val ldt = TimeConverter.convertUTCToLocalDateTime(
        weather.dt,
        weather.timezone,
    )
    val tz: TimeZone = FixedOffsetTimeZone(UtcOffset(seconds = weather.timezone))
    val dateText = if (ldt == Clock.System.now().toLocalDateTime(tz)) {
        "오늘"
    } else {
        ldt.dayOfWeekKo(DayNameStyle.SHORT)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = dateText,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        WeatherIconWithRainPercentage(
            weather = weather,
            onDayClick = {

            },
            modifier = Modifier
                .wrapContentWidth()
        )
        Spacer(modifier = Modifier.width(32.dp))
        Text(
            text = "${weather.main.tempMin.toInt()}",
            color = Temp_Text_min
        )
        Spacer(modifier = Modifier.width(8.dp))
        TemperatureProgressBar(
            currTemp = weather.main.temp,
            modifier = Modifier
                .width(100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${weather.main.tempMax.toInt()}",
            color = Color.White
        )
    }
}

@Composable
private fun TemperatureProgressBar(
    currTemp: Double? = null,
    modifier: Modifier = Modifier
) {
    if (currTemp == null) {
        LinearProgressIndicator(
            color = Color.Gray,
            trackColor = Color.Unspecified,
            strokeCap = StrokeCap.Round,
            gapSize = 4.dp,
            modifier = modifier
        )
    } else {
        LinearProgressIndicator(
            progress = {
                currTemp.toFloat()
            },
            color = Color.Gray,
            trackColor = Color.Unspecified,
            strokeCap = StrokeCap.Round,
            gapSize = 4.dp,
            modifier = modifier
        )
    }
}

@Composable
private fun WeatherIconWithRainPercentage(
    weather: CurrentWeatherModel,
    onDayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .wrapContentSize()
            .clickable {
                onDayClick()
            }
    ) {
        AsyncImage(
            model = ImageConverter.openWeatherIconUrl(weather.weather.first().icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )
        val rainAmount: Double = weather.rain?.oneHour ?: 0.0
        Text(
            text = "${(rainAmount * 100).toInt()}%",
            color = Rain_Text_SkyBlue,
            fontSize = 12.sp
        )
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun WeatherDailySunWindInfo(
    timezone: Int,
    sunrise: Long,
    sunset: Long,
    wind: WindModel,
    modifier: Modifier = Modifier
) {
    val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val sunriseLdt = TimeConverter.convertUTCToLocalDateTime(sunrise, timezone)
    val sunsetLdt = TimeConverter.convertUTCToLocalDateTime(sunset, timezone)

    val isGonnaSunset = (currentTime in sunriseLdt..sunsetLdt)

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        GlassPanel(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Rounded.WbSunny,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isGonnaSunset) "일몰" else "일출",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (isGonnaSunset) {
                    "오후 ${TimeConverter.hm(sunsetLdt.hour, sunsetLdt.minute)}"
                } else {
                    "오전 ${TimeConverter.hm(sunriseLdt.hour, sunriseLdt.minute)}"
                },
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (isGonnaSunset) {
                    "일출: 오전 ${TimeConverter.hm(sunriseLdt.hour, sunriseLdt.minute)}"
                } else {
                    "일몰: 오후 ${TimeConverter.hm(sunsetLdt.hour, sunsetLdt.minute)}"
                },
                color = Color.White
            )
        }

        GlassPanel(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Rounded.WindPower,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "바람",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
               verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "바람",
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${wind.speed}m/s",
                    color = Color.White
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "돌풍",
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${wind.gust}m/s",
                    color = Color.White
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "방향",
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${getWindDirectionText(Language.KR, wind.deg)}풍 ${wind.deg}°",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun WeatherFeelsLikeRainInfo(
    currentWeather: CurrentWeatherModel,
    modifier: Modifier = Modifier
) {
    val temp = currentWeather.main.temp
    val feelsLike = currentWeather.main.feelsLike
    val feelsLikeText = if (temp < feelsLike) {
        "높게"
    } else "낮게"

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        GlassPanel(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Rounded.Thermostat,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "체감 온도",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${feelsLike.toInt()}°C",
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "실제 기온보다 더 $feelsLikeText 느껴지겠습니다.",
                fontSize = 12.sp,
                color = Color.White
            )
        }

        GlassPanel(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Rounded.WaterDrop,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "강수량",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${currentWeather.rain?.oneHour?.toInt() ?: 0}mm",
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                color = Color.White
            )
            Text(
                text = "지난 1시간",
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun WeatherHumidityPressureInfo(
    humidity: Int,
    pressure: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        GlassPanel(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.WbCloudy,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "습도",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${humidity}%",
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                color = Color.White
            )
//            Spacer(modifier = Modifier.weight(1f))
//            Text(
//                text = "현재 이슬점이 ${dewPoint}°C입니다.",
//                fontSize = 12.sp,
//                color = Color.White
//            )
        }

        GlassPanel(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Air,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "기압",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${pressure.withComma()}\n",
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "hPa",
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
        }
    }
}