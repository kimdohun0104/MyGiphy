package com.dsm.domain

import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import io.reactivex.Flowable

fun <T> Flowable<T>.toResource(errorHandler: ErrorHandler, data: T?) =
    this.map<Resource<T>> { Resource.Success(it) }
        .onErrorReturn {
            data?.let { data -> return@onErrorReturn Resource.Success(data, true) }
            Resource.Error(errorHandler.getError(it))
        }