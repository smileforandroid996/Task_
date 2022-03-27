package com.mohamed.task.ui.utils

import retrofit2.HttpException


class AppUtils {



    fun handleError(throwable: Throwable): String {
        return if (throwable is HttpException) {
            when (throwable.code()) {
                400 -> {
                    "Error"
                }
                401 -> {
                    "Not Auth"
                }
                403 -> {
                    "Forbidden"
                }
                404 -> {
                    "Not Found"
                }
                500 -> {
                    "Server Error"
                }
                else -> {
                    throwable.message!!
                }
            }
        } else {
            throwable.message!!
        }
    }
}