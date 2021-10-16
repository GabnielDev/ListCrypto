package com.stockbit.hiring.rest

import com.stockbit.hiring.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("top/totaltoptiervolfull?limit=10&tsym=IDR")
    suspend fun getTop(): retrofit2.Response<Response>
}