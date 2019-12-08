package com.dsm.domain.error

import retrofit2.HttpException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity =
        when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorEntity.Internal(throwable)
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound(throwable)
                    else -> ErrorEntity.Internal(throwable)
                }
            }
            else -> ErrorEntity.Internal(throwable)
        }

}