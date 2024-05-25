package com.example.chetanbhagat.Videos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chetanbhagat.R
import com.example.chetanbhagat.RetrofitInstance
import com.example.chetanbhagat.adapter.VideosAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideosActivity : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_videos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv)


getData()

    }

    private fun getData() {
        RetrofitInstance.apiInterface.Videos().enqueue(object : Callback<VideosPojo?> {
            override fun onResponse(p0: Call<VideosPojo?>, p1: Response<VideosPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val LinearLayoutManager = LinearLayoutManager(this@VideosActivity)
                    recyclerView.layoutManager = LinearLayoutManager

                    val QuotesAdapter = VideosAdapter(this@VideosActivity, p1.body()!!)
                    recyclerView.adapter = QuotesAdapter

                    Toast.makeText(this@VideosActivity, "success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@VideosActivity, "error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<VideosPojo?>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })


}
}