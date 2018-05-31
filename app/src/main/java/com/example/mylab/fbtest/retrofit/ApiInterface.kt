package com.example.mylab.fbtest.retrofit

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.example.mylab.fbtest.paging.PageData
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Abins Shaji on 21/05/18.
 */
interface ApiInterface {

    @GET("https://my-maid-azinova.firebaseio.com/pagedata.json")
    fun getData():Single<List<PageData>>
}