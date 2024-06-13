package com.example.e_commerce.data.model



// sealed avery class inhertins from it
sealed class Recourse<T>(val data:T?=null,
                  val exception:Exception?=null) {

    class Success<T>(data: T?): Recourse<T>(data)
    class Loading<T>(data: T?=null): Recourse<T>(data)
    class Error<T>(message: Exception,data: T?=null): Recourse<T>(data,message)

}