package com.example.mylab.fbtest.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mylab.fbtest.R
import kotlinx.android.synthetic.main.adapter_layout.view.*

/**
 * Created by Abins Shaji on 07/05/18.
 */
class RecyAdapter(var data:ArrayList<Int>,val context: Context):RecyclerView.Adapter<RecyAdapter.Data>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Data {
        val view=LayoutInflater.from(parent?.context).inflate(R.layout.adapter_layout,parent,false)
        return Data(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: Data, position: Int) {
        holder?.setData(data.get(position))
    }


    class Data(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        fun setData(data:Int)
        {
            itemView.text.text=data.toString()

        }

    }




}