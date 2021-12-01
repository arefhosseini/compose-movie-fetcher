package com.fearefull.composemoviefetcher.base

import java.lang.Exception

sealed class ResponseResult<out R> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Failure(val exception: Exception) : ResponseResult<Nothing>()
}