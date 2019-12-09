package com.dsm.domain.error

import com.dsm.domain.error.exception.BadRequestException
import com.dsm.domain.error.exception.ForbiddenException
import com.dsm.domain.error.exception.InternalException
import com.dsm.domain.error.exception.NotFoundException
import retrofit2.HttpException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {
    override fun getError(throwable: Throwable): Exception =
        when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> InternalException(throwable)

                    HttpURLConnection.HTTP_FORBIDDEN -> ForbiddenException(throwable)

                    HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException(throwable)

                    HttpURLConnection.HTTP_BAD_REQUEST -> BadRequestException(throwable)

                    else -> InternalException(throwable)
                }
            }
            else -> InternalException(throwable)
        }

}