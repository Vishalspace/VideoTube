package com.chat.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: VideoRepo

    val adapter = VideoAdapter(mutableListOf(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        (application as App).appComponent.inject(this)
        repo.getVideosLiveData().observe(this) {
            adapter.updateList(it)
            log("updated adapter with data: $it")
        }
    }

    companion object {
        fun log(msg: String) {
            Log.i(MainActivity::class.java.name, msg)
        }

    }
}