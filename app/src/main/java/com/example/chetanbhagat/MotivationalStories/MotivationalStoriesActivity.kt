package com.example.chetanbhagat.MotivationalStories

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
import com.example.chetanbhagat.adapter.MotivationalAdapter
import com.example.chetanbhagat.adapter.QuotesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MotivationalStoriesActivity : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_motivational_stories)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv)
        getdata()
    }


    private fun getdata() {
        RetrofitInstance.apiInterface.Motivational().enqueue(object : Callback<MotivationalPojo?> {
            override fun onResponse(p0: Call<MotivationalPojo?>, p1: Response<MotivationalPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val LinearLayoutManager = LinearLayoutManager(this@MotivationalStoriesActivity)
                    recyclerView.layoutManager = LinearLayoutManager

                    val MotivationalAdapter = MotivationalAdapter(this@MotivationalStoriesActivity, p1.body()!!)
                    recyclerView.adapter = MotivationalAdapter

                    Toast.makeText(this@MotivationalStoriesActivity, "success", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Toast.makeText(this@MotivationalStoriesActivity, "error", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(p0: Call<MotivationalPojo?>, p1: Throwable) {
                Toast.makeText(this@MotivationalStoriesActivity, ""+p1.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
