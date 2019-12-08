package com.dsm.mygiphy

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

fun createHttpException(errorCode: Int): HttpException {
    val errorResponse: Response<Unit> = Response.error(
        errorCode,
        "".toResponseBody("application/json".toMediaTypeOrNull())
    )

    return HttpException(errorResponse)
}
