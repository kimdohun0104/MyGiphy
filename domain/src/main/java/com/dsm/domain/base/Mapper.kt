package com.dsm.domain.base

interface Mapper<F, T> {
    fun mapFrom(from: F): T
}