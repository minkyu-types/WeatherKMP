package com.samsung.weather_data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWeatherByLLMDto(
    // LLM 생성 답변
    val answer: String? = null,

    // 모델이 답변을 생성할 때 사용한 날씨 데이터(부분 집합일 수 있음)
    val data: DataPayload? = null,

    // 질의에 포함된 위치명(명세의 "loc1Location 1 name from inquiry"를 loc1로 가정)
    @SerialName("loc1") val loc1: String? = null,

    // 참고: 어떤 구현체는 날씨 필드를 top-level에도 평면으로 넣을 수 있어 양쪽 모두 수용
    val lat: Double? = null,
    val lon: Double? = null,
    val timezone: String? = null,
    @SerialName("timezone_offset") val timezoneOffset: Int? = null,

    // (선택) 현재 시각/요일 등의 메타가 별도 필드로 올 수 있음 → 문자열로 안전 파싱
    @SerialName("current UTC Time") val currentUtcTime: String? = null,
    @SerialName("current Week Day UTC") val currentWeekDayUtc: String? = null,

    // 날씨 섹션이 top-level에 직접 담길 수도 있어 널로 수용
    val current: CurrentDto? = null,
    val minutely: List<MinutelyDto>? = null,
    val hourly: List<HourlyDto>? = null,
    val daily: List<DailyDto>? = null,
    val alerts: List<AlertDto>? = null,

    @SerialName("session_id") val sessionId: String? = null
)

/* ========== data 필드(답변 생성에 사용된 부분 데이터) ========== */

@Serializable
data class DataPayload(
    val lat: Double? = null,
    val lon: Double? = null,
    val timezone: String? = null,
    @SerialName("timezone_offset") val timezoneOffset: Int? = null,

    val current: CurrentDto? = null,
    val minutely: List<MinutelyDto>? = null,
    val hourly: List<HourlyDto>? = null,
    val daily: List<DailyDto>? = null,
    val alerts: List<AlertDto>? = null
)