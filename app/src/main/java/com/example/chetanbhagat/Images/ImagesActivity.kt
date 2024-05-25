package com.example.chetanbhagat.Images

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
import com.example.chetanbhagat.adapter.ImagesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesActivity : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_images)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv)

        getData()

    }


    private fun getData() {
        RetrofitInstance.apiInterface.Images().enqueue(object : Callback<ImagesPojo?> {
            override fun onResponse(p0: Call<ImagesPojo?>, p1: Response<ImagesPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val LinearLayoutManager = LinearLayoutManager(this@ImagesActivity)
                    recyclerView.layoutManager = LinearLayoutManager

                    val ImagesAdapter = ImagesAdapter(this@ImagesActivity, p1.body()!!)
                    recyclerView.adapter = ImagesAdapter

                    Toast.makeText(this@ImagesActivity, "success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@ImagesActivity, "error", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(p0: Call<ImagesPojo?>, p1: Throwable) {
                Toast.makeText(this@ImagesActivity, ""+p1.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}
