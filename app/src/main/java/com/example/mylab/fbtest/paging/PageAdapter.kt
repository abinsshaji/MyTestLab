package com.example.mylab.fbtest.paging

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mylab.fbtest.R
import kotlinx.android.synthetic.main.adapter_layout.view.*

/**
 * Created by Abins Shaji on 21/05/18.
 */
class PageAdapter(val context:Context,val arrayList: List<PageData>): PagedListAdapter<PageData,PageAdapter.DataHolder>(userDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout,parent,false)
        return DataHolder(view)

    }

    override fun getItemCount(): Int {
       return arrayList.size

    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.setData(arrayList.get(position))



    }

    class DataHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        fun setData( data:PageData)
        {
            itemView.text.text=data.data

        }
    }
    companion object {
        val userDiffCallback=object :DiffUtil.ItemCallback<PageData>(){
            override fun areItemsTheSame(oldItem: PageData?, newItem: PageData?): Boolean {
               return oldItem==newItem

            }

            override fun areContentsTheSame(oldItem: PageData?, newItem: PageData?): Boolean {
                return  oldItem==newItem

            }
        }
    }
}