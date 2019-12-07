package com.dsm.data.remote

import com.dsm.data.remote.entity.GifListData
import com.dsm.data.remote.entity.SingleGifData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyApi {

    @GET("v1/gifs/trending")
    fun getTrendGifs(@Query("offset") page: Int): Flowable<GifListData>

    @GET("v1/gifs/{gif_id}")
    fun getGifDetail(@Path("gif_id") gifId: String): Flowable<SingleGifData>
}