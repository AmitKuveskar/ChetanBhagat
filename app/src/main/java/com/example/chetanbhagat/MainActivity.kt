package com.example.chetanbhagat

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chetanbhagat.Books.BooksActivity
import com.example.chetanbhagat.Images.ImagesActivity
import com.example.chetanbhagat.MotivationalStories.MotivationalStoriesActivity
import com.example.chetanbhagat.Quotes.QuotesActivity

class MainActivity : AppCompatActivity() {
    lateinit var BookImagebtn: CardView
    lateinit var Quotesbtn: CardView
    lateinit var Motivationalbtn: CardView
    lateinit var Imagesbtn: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BookImagebtn = findViewById(R.id.card1)
        Quotesbtn = findViewById(R.id.card2)
        Motivationalbtn = findViewById(R.id.card4)
        Imagesbtn = findViewById(R.id.card5)

        BookImagebtn.setOnClickListener {
            val intent = Intent(this@MainActivity, BooksActivity::class.java)
            startActivity(intent)
        }

        Quotesbtn.setOnClickListener {
            val intent = Intent(this@MainActivity, QuotesActivity::class.java)
            startActivity(intent)
        }

        Motivationalbtn.setOnClickListener {
            val intent = Intent(this@MainActivity, MotivationalStoriesActivity::class.java)
            startActivity(intent)
        }

        Imagesbtn.setOnClickListener {
            val intent = Intent(this@MainActivity, ImagesActivity::class.java)
            startActivity(intent)
        }

        }

}