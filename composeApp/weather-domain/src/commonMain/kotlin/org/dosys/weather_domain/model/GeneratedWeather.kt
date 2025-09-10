package org.dosys.weather_domain.model

data class GeneratedWeather(
    // LLM 생성 답변
    val answer: String? = null,

    // 모델이 답변을 생성할 때 사용한 날씨 데이터(부분 집합일 수 있음)
    val data: DataPayload? = null,

    // 질의에 포함된 위치명(명세의 "loc1Location 1 name from inquiry"를 loc1로 가정)
    val loc1: String? = null,

    // 참고: 어떤 구현체는 날씨 필드를 top-level에도 평면으로 넣을 수 있어 양쪽 모두 수용
    val lat: Double? = null,
    val lon: Double? = null,
    val timezone: String? = null,
    val timezoneOffset: Int? = null,

    // (선택) 현재 시각/요일 등의 메타가 별도 필드로 올 수 있음 → 문자열로 안전 파싱
    val currentUtcTime: String? = null,
    val currentWeekDayUtc: String? = null,

    // 날씨 섹션이 top-level에 직접 담길 수도 있어 널로 수용
    val current: Current? = null,
    val minutely: List<Minutely>? = null,
    val hourly: List<Hourly>? = null,
    val daily: List<Daily>? = null,
    val alerts: List<Alert>? = null,

    val sessionId: String? = null
)

/* ========== data 필드(답변 생성에 사용된 부분 데이터) ========== */
data class DataPayload(
    val location: Location? = null,
    val timezone: String? = null,
    val timezoneOffset: Int? = null,
    val current: Current? = null,
    val minutely: List<Minutely>? = null,
    val hourly: List<Hourly>? = null,
    val daily: List<Daily>? = null,
    val alerts: List<Alert>? = null
)