package com.example.mylab.fbtest.paging

import android.arch.paging.ItemKeyedDataSource
import com.example.mylab.fbtest.retrofit.ApiClass
import com.example.mylab.fbtest.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by Abins Shaji on 21/05/18.
 */
class MyDataSource : ItemKeyedDataSource<String,PageData>() {
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<PageData>) {

        val item=getData()

        if (item != null) {
            callback.onResult(item)
        }



    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<PageData>) {

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<PageData>) {

    }

    override fun  getKey(item: PageData): String {
        return item.data

    }


    fun getData() : List<PageData>?
    {
        var lists: List<PageData>? = null

        ApiClass.getApiService().create(ApiInterface::class.java).getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {

                        lists=it
                })
        return lists



    }
}