package com.stockbit.hiring.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockbit.hiring.model.Data
import com.stockbit.hiring.rest.ApiClient
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CoinViewModel: ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val status = MutableLiveData<Int>()
    private val message = MutableLiveData<String>()

    fun getCoin(): LiveData<ArrayList<Data>> {
        val coin = MutableLiveData<ArrayList<Data>>()
        loading.value = true
        viewModelScope.launch {
            try {
                val data = ApiClient.getClient().getTop()
                if (data.isSuccessful) {
                    coin.value = data.body()?.Data
                } else {
                    status.value = data.code()
                }
                loading.value = false
            } catch (t: Throwable) {
                when(t) {
                    is IOException -> message.value = t.message.toString()
                    is HttpException ->message.value = t.message().toString()
                    else -> message.value ="Unknow Error"
                }
                loading.value = false
            }
        }
        return coin
    }

    fun getLoading(): LiveData<Boolean> = loading
    fun getStatus(): LiveData<Int> = status
    fun getMessage(): LiveData<String> = message

}