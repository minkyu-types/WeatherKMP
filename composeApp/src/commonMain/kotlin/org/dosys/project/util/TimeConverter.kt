package org.dosys.project.util

import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object TimeConverter {
    /**
     * KMP 용 date & time 변환을 위한 라이브러리, kotlinx-datetime
     *
     * 오픈 API: openweathermap.org
     *
     * ```
     * "timezone":"America/Chicago",
     * "timezone_offset":-18000,
     * "current":{
     *  "dt":1684929490
     *  . . .
     * }
     * ```
     * 1. UTC 타임 포맷을 사용하는 경우, 1000으로 나누는 이유
     * 2. TimeZone이란?
     * 2-1. TimeZone 변환 중 예외 발생 시 사용하는 FixedOffsetTimeZone, ZoneOffset 이란?
     * 3. Instant, EpochSecond란?
     *
     * @return ex) "Sep 8, 06:17am"
     */
    @OptIn(ExperimentalTime::class)
    fun getCurrentDateTimeFromUTC(
        utcDateTime: Long,
        zone: String,
        zoneOffset: Int
    ): String {
        val epochSeconds = if (utcDateTime > 1_000_000_000_000L) utcDateTime / 1000 else utcDateTime
        val tz: TimeZone = runCatching { TimeZone.of(zone) }
            .getOrElse { FixedOffsetTimeZone(UtcOffset(seconds = zoneOffset)) }
        val ldt = Instant.fromEpochSeconds(epochSeconds).toLocalDateTime(tz)
        val month = when(ldt.month.number) {
            1 -> "Jan" 2 -> "Feb" 3 -> "Mar" 4 -> "Apr" 5 -> "May"
            6 -> "Jun" 7 -> "Jul" 8 -> "Aug" 9 -> "Sep" 10 -> "Oct"
            11 -> "Nov" else -> ""
        }
        val hour12 = ((ldt.hour + 11) % 12) + 1 // 23 -> 11, 22 -> 10, 0 -> 12
        val ampm = if (ldt.hour < 12) "am" else "pm"

        return "$month ${ldt.day}, $hour12:${ldt.minute.toString().padStart(2, '0')} $ampm"
    }

    @OptIn(ExperimentalTime::class)
    fun convertUTCToLocalDateTime(
        utcDateTime: Long,
        zone: String,
        zoneOffset: Int
    ): LocalDateTime {
        val epochSeconds = if (utcDateTime > 1_000_000_000_000L) utcDateTime / 1000 else utcDateTime
        val tz: TimeZone = runCatching { TimeZone.of(zone) }
            .getOrElse { FixedOffsetTimeZone(UtcOffset(seconds = zoneOffset)) }
        val ldt = Instant.fromEpochSeconds(epochSeconds).toLocalDateTime(tz)

        return ldt
    }
}