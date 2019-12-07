package com.dsm.domain.error

sealed class ErrorEntity {

    abstract val exception: Throwable

    data class Internal(override val exception: Throwable) : ErrorEntity()

    data class NotFound(override val exception: Throwable) : ErrorEntity()
}