package com.dsm.data.remote

import android.content.Context
import com.dsm.data.R
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", context.getString(R.string.api_key)).build()
        return chain.proceed(request.newBuilder().url(url).build())
    }

}
