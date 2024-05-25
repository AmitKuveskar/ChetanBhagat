package com.example.chetanbhagat.Books


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.chetanbhagat.MainActivity
import com.example.chetanbhagat.R
import com.example.chetanbhagat.RetrofitInstance
import com.example.chetanbhagat.adapter.BookImageAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class BooksActivity : AppCompatActivity() {
    lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var adapter: BookImageAdapter
    lateinit var Backbtn : ImageView
    lateinit var WebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_books)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        WebView = findViewById(R.id.webview)

        viewPager2 = findViewById(R.id.viewPager2)
        Backbtn = findViewById(R.id.backicon)

        Backbtn.setOnClickListener {
            val  intent = Intent(this@BooksActivity, MainActivity::class.java)
            startActivity(intent)
        }

        // Retrieve the book_link_url from the intent extra
        val bookLinkUrl = intent.getStringExtra("BookLinkUrl")

        // Configure the WebView
        WebView.settings.javaScriptEnabled = true
        WebView.webViewClient = WebViewClient()
        // Load the URL into the WebView
        if (bookLinkUrl != null) {
            WebView.loadUrl(bookLinkUrl)
        }


        init()
        setUpTransformer()
    }

    private fun setUpTransformer() {
        val transformer = DepthPageTransformer()
        viewPager2.setPageTransformer(transformer)
    }

    private fun init() {
        handler = Handler(Looper.myLooper()!!)
        val imageUrls = ArrayList<String>()

        RetrofitInstance.apiInterface.Books().enqueue(object : Callback<BooksPojo?> {
            override fun onResponse(call: Call<BooksPojo?>, response: Response<BooksPojo?>) {
                if (response.isSuccessful && response.body() != null) {
                    val booksPojo = response.body()!!

                    // Iterate through each BooksPojoItem and extract the image URLs
                    for (book in booksPojo) {
                        imageUrls.add(book.book_link_image)
                    }

                    // Initialize and set the adapter with the fetched image URLs
                    adapter = BookImageAdapter(this@BooksActivity, booksPojo,viewPager2)
                    viewPager2.adapter = adapter
                } else {
                    // Handle API error
                    Toast.makeText(this@BooksActivity, "Failed to fetch images", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BooksPojo?>, t: Throwable) {
                // Handle API call failure
                Toast.makeText(this@BooksActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        // Optional: Set some additional properties for `ViewPager2`
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Set the initial page for `ViewPager2`
        viewPager2.setCurrentItem(3, true)
    }



}