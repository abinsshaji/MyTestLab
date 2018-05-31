package com.example.mylab.fbtest.recycler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mylab.fbtest.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recy.*

class RecyActivity : AppCompatActivity() {
    var data= arrayListOf<Int>(1,2,3,4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recy)


        setAdapter()

        b_add.setOnClickListener {
            data.add(data.get(data.size-1)+1)
            recy_recycler.adapter.notifyDataSetChanged()


        }
        b_remove.setOnClickListener {
            data.remove(data.get(data.size-1))
            recy_recycler.adapter.notifyDataSetChanged()

        }

        volleyTest()



    }
    fun volleyTest()
    {
        val req=Volley.newRequestQueue(this)
        val url="https://www.spectrumservices.ae/api/crew_in?ws=spec09645d17359d1ac3d2516d0b7f2"

        val jsonObjectReq=JsonObjectRequest(Request.Method.GET,url,null,Response.Listener { 
            response ->
            Log.e("volleydata",response.toString())

        }, Response.ErrorListener {

            Log.e("volleydata","error")
        })

        req.add(jsonObjectReq)

    }

    fun setAdapter()
    {

        recy_recycler.layoutManager=LinearLayoutManager(this)
        recy_recycler.adapter=RecyAdapter(data,this)
    }
}
