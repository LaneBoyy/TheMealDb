package com.laneboy.themealdb.data.wrapper

open class NoInternetException(
    errorCreationTime: Long = System.currentTimeMillis()
) : ApiException(
    message = "Отсутствует подключение к интернету",
    errorCreationTime = errorCreationTime
)
