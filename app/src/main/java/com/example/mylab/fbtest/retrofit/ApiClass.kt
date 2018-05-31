package com.example.mylab.fbtest.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Abins Shaji on 13/03/18.
 */
class ApiClass {

    companion object {
        val URL="https://spectrum-d498e.firebaseio.com"


        fun getApiService():Retrofit
        {
            val  retrofit=Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()


            return retrofit

        }

    }


}