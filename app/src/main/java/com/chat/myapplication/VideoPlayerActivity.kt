package com.chat.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.chat.myapplication.databinding.ActivityVideoPlayerBinding
import com.chat.myapplication.model.Video
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.*
import com.google.android.youtube.player.YouTubePlayerView

class VideoPlayerActivity : YouTubeBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBindingAndStartPlayer(binding)
    }

    private fun initBindingAndStartPlayer(binding: ActivityVideoPlayerBinding) {
        val video = getVideo()
        binding.videotitle.text = video.videoTitle
        binding.channelname.text = video.channelTitle
        startYoutubePlayer(binding.youtubeView, video)
    }

    private fun startYoutubePlayer(view: YouTubePlayerView, video: Video) {
        view.initialize(KEY, object: OnInitializedListener {
            override fun onInitializationSuccess(p: Provider?, yp: YouTubePlayer?, res: Boolean) {
                yp?.loadVideo(video.videoId)
            }

            override fun onInitializationFailure(p: Provider?, r: YouTubeInitializationResult?) {
                log("Error playing video: $r")
                Toast.makeText(this@VideoPlayerActivity, "Player error", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getVideo(): Video {
        val videoStr = intent.getStringExtra("video")
        return Video.fromJson(videoStr!!)!!
    }

    companion object {
        private const val KEY = "AIzaSyByRHdigcXKrWPujp2wY0c5FnVxcn6tLBw"
        fun log(msg: String) {
            Log.e(VideoPlayerActivity::class.java.name, msg)
        }
    }
}
