package com.chat.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chat.myapplication.databinding.VideocardBinding
import com.chat.myapplication.model.Video


class VideoAdapter(private val videolist : MutableList<Video>, private val context: Context): RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.videocard,parent,false)
//        return VideoViewHolder(v)
        return VideoViewHolder(VideocardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.binding.channelname.text = videolist[position].videoTitle
        holder.binding.videotitle.text = videolist[position].channelTitle
        val url = videolist[position].thumbnailUrl
        Glide.with(context).load(url).into(holder.binding.thumbnail)
        holder.binding.root.setOnClickListener {
            val intent = Intent(context,VideoPlayerActivity::class.java)
            intent.putExtra("video", Video.toJson(videolist[position]))
            println(Video.toJson(videolist[position]))
            //Toast.makeText(context, Video.toJson(videolist[position]), Toast.LENGTH_LONG).show()
            startActivity(context,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return videolist.size
    }

    fun updateList(v: List<Video>){
        videolist.clear()
        videolist.addAll(v)
        notifyDataSetChanged()
    }


    inner class VideoViewHolder(val binding : VideocardBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}