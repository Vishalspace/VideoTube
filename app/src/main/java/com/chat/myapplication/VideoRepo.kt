package com.chat.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import com.chat.myapplication.api.ApiInterface
import com.chat.myapplication.db.VideoDao
import com.chat.myapplication.model.Video
import com.chat.myapplication.model.VideoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class VideoRepo @Inject constructor(api: ApiInterface, private val videoDao: VideoDao) {
    init {
        api.getVideos().enqueue(object : Callback<List<VideoResponse>> {
            override fun onResponse(call: Call<List<VideoResponse>>, response: Response<List<VideoResponse>>) {
                Executors.newCachedThreadPool().submit {
                    videoDao.insertVideos(response.body()?.map { it.toVideo() } ?: emptyList())
                    log("inserted ${response.body()!!.toString()} into dao")
                }
            }

            override fun onFailure(call: Call<List<VideoResponse>>, t: Throwable) {
                log("Error: $t")
            }
        })
    }

    fun getVideosLiveData(): LiveData<List<Video>> {
        return videoDao.fetchvideos()
    }

    companion object {
        fun log(msg: String) {
            Log.e(VideoRepo::class.java.simpleName, msg)
        }
    }
}