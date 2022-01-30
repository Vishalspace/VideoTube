package com.chat.myapplication.api

import com.chat.myapplication.model.VideoResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("8d17a1a4-a1bc-490c-8c7a-09917f9cb1aa")
    fun getVideos() : Call<List<VideoResponse>>
}