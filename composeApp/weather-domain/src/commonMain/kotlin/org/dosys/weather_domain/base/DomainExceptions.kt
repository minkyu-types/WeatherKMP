package org.dosys.weather_domain.base

sealed class DomainException(message: String?, cause: Throwable? = null) : Exception(message, cause) {
    class Network(message: String? = null, cause: Throwable? = null) : DomainException(message, cause)
    class Timeout(message: String? = null, cause: Throwable? = null) : DomainException(message, cause)
    class Http(code: Int, reason: String? = null) : DomainException("HTTP $code: $reason")
    class Serialization(message: String? = null, cause: Throwable? = null) : DomainException(message, cause)
    class Unknown(message: String, cause: Throwable? = null) : DomainException(message, cause)
}