package com.dsm.data.remote

import com.dsm.data.remote.entity.GifListData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("v1/gifs/trending")
    fun getTrendGifs(
        @Query("offset") page: Int,
        @Query("limit") pageSize: Int
    ): Flowable<GifListData>

}