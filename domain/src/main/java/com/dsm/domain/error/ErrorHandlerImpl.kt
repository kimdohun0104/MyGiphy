package com.dsm.domain.error

import java.net.HttpURLConnection
import javax.xml.ws.http.HTTPException

class ErrorHandlerImpl : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity =
        when (throwable) {
            is HTTPException -> {
                when (throwable.statusCode) {
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorEntity.Internal(throwable)
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound(throwable)
                    else -> ErrorEntity.Internal(throwable)
                }
            }
            else -> ErrorEntity.Internal(throwable)
        }

}