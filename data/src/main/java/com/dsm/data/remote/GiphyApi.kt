package com.dsm.data.remote

import com.dsm.data.remote.entity.GifListData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("v1/gifs/trending")
    fun getTrendGifList(@Query("offset") offset: Int): Flowable<GifListData>

    @GET("v1/gifs/search")
    fun searchGifList(
        @Query("offset") offset: Int,
        @Query("q") q: String
    ): Flowable<GifListData>
}