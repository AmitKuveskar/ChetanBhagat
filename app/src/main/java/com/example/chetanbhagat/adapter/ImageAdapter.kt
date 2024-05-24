package com.example.chetanbhagat.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

import com.bumptech.glide.Glide
import com.example.chetanbhagat.Books.BooksPojo
import com.example.chetanbhagat.R


class ImageAdapter(private val context: Context, private var BooksPojo: BooksPojo, private val viewPager2: ViewPager2) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val BooksImage : ImageView = itemView.findViewById(R.id.imageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seasonal_image,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Books = BooksPojo[position]
        Glide.with(holder.itemView)
            .load(Books.book_link_image) // Load image from URL
            .into(holder.BooksImage) // Set image into ImageView

        holder.BooksImage.setOnClickListener {
            // Get the corresponding BooksPojoItem
            val clickedBook = BooksPojo[position]
            // Extract the book_link_url
            val bookLinkUrl = clickedBook.book_link_url
            // Open the book_link_url in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(bookLinkUrl))
            context.startActivity(intent)
        }




        // Handle the end of the list scenario
        if (position == BooksPojo.size - 1) {
            viewPager2.post(runnable)
        }

    }



    override fun getItemCount(): Int {
        return BooksPojo.size
    }

    private val runnable = Runnable {
        BooksPojo.addAll(BooksPojo)
        notifyDataSetChanged()
    }
}




