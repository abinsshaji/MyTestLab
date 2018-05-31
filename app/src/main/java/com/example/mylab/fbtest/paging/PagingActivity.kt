package com.example.mylab.fbtest.paging

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.mylab.fbtest.R
import com.example.mylab.fbtest.retrofit.ApiClass
import com.example.mylab.fbtest.retrofit.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : AppCompatActivity() {

    val disposable=CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
    }

    fun getData()
    {
        disposable.add(ApiClass.getApiService().create(ApiInterface::class.java).getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    setAdapter(it)

                }))


    }

    fun setAdapter(data:List<PageData>)
    {
        pageRecycler.layoutManager=LinearLayoutManager(this)
        val adapter=PageAdapter(this,data)


    }
}
