package org.dosys.base_data

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import org.dosys.base_domain.DomainException

sealed interface DataExceptions {

    data object NetworkException: DataExceptions
    data object TimeoutException: DataExceptions
    data object SerializationException: DataExceptions
    data object UnknownException: DataExceptions
}

fun Throwable.toDomainException(): DomainException = when (this) {
    is kotlinx.coroutines.CancellationException -> throw this
    is RedirectResponseException -> DomainException.Http(response.status.value, response.status.description) // 3xx
    is ClientRequestException -> DomainException.Http(response.status.value, response.status.description) // 4xx
    is ServerResponseException -> DomainException.Http(response.status.value, response.status.description) // 5xx
    is ResponseException -> DomainException.Http(response.status.value, response.status.description)
    is HttpRequestTimeoutException -> DomainException.Timeout(message, this)
    is IOException -> DomainException.Network(message, this)
    is SerializationException -> DomainException.Serialization(message, this)
    else -> DomainException.Unknown(message ?: "Unknown Error", this)
}