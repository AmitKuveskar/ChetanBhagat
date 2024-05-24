package com.example.chetanbhagat.Quotes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chetanbhagat.Books.BooksPojo
import com.example.chetanbhagat.R
import com.example.chetanbhagat.RetrofitInstance
import com.example.chetanbhagat.adapter.ImageAdapter
import com.example.chetanbhagat.adapter.QuotesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuotesActivity : AppCompatActivity() {

    lateinit var  recyclerView: RecyclerView
    private lateinit var adapter: QuotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quotes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv)

        getData()
    }


    private fun getData() {
        RetrofitInstance.apiInterface.Quotes().enqueue(object : Callback<QuotesPojo?> {
            override fun onResponse(p0: Call<QuotesPojo?>, p1: Response<QuotesPojo?>) {
                if (p1.code() == 200 && p1.body() != null) {

                    val LinearLayoutManager = LinearLayoutManager(this@QuotesActivity
                    )
                    recyclerView.layoutManager = LinearLayoutManager

                    val QuotesAdapter = QuotesAdapter(this@QuotesActivity, p1.body()!!)
                    recyclerView.adapter = QuotesAdapter

                    Toast.makeText(this@QuotesActivity, "success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@QuotesActivity, "error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<QuotesPojo?>, p1: Throwable) {
                Toast.makeText(this@QuotesActivity, ""+p1.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}