package com.dsm.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", "lXmZ4fvgFkzK9tzTQWK25SjzXkFO8pBN").build()
        return chain.proceed(request.newBuilder().url(url).build())
    }

}
