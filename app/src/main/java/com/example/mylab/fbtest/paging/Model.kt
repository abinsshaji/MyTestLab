package com.example.mylab.fbtest.paging

import com.google.gson.annotations.SerializedName

/**
 * Created by Abins Shaji on 21/05/18.
 */
data class Model(val list:ArrayList<PageData>)
data class PageData(@SerializedName("data") val data:String)