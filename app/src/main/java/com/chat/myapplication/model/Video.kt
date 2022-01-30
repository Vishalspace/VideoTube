package com.chat.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

val gson = Gson()

data class VideoResponse(
    val id: ID,
    val snippet: SnippetResponse
) {
    data class SnippetResponse(
        val channelTitle: String,
        val description: String,
        val thumbnails: Map<String, Thumb>,
        @SerializedName("title")val videotitle:String
    )

    data class Thumb(
        val url : String
    )

    data class ID(
        val videoId: String
    )

    fun toVideo(): Video {
        return Video(id.videoId, snippet.videotitle,snippet.channelTitle,snippet.description,snippet.thumbnails["high"]!!.url)
    }
}

@Entity
data class Video(
    @PrimaryKey val videoId: String,
    val videoTitle: String,
    val channelTitle: String,
    val description: String,
    val thumbnailUrl: String,
) {

    companion object {

        fun toJson(video: Video): String {
            return gson.toJson(video)
        }

        fun fromJson(str: String): Video? {
            return gson.fromJson(str, Video::class.java)
        }
    }
}
