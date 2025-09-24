package org.dosys.base_domain

sealed class CommonDomainException(message: String?, cause: Throwable? = null) :
    Exception(message, cause), DomainException {
    data class Network(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : CommonDomainException(message, cause)

    data class Timeout(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) :
        CommonDomainException(message, cause)

    data class Http(val code: Int, val reason: String? = null) :
        CommonDomainException("HTTP $code: $reason")

    data class Serialization(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) :
        CommonDomainException(message, cause)

    data class Unknown(override val message: String, override val cause: Throwable? = null) :
        CommonDomainException(message, cause)
}