package com.dsm.mygiphy

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

fun createHttpException(errorCode: Int): HttpException {
    val errorResponse: Response<Unit> = Response.error(
        errorCode,
        ResponseBody.create(MediaType.parse("application/json"), "")
    )

    return HttpException(errorResponse)
}
