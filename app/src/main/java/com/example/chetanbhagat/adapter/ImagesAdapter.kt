package com.example.chetanbhagat.adapter

import android.content.Context
import android.provider.MediaStore.Images
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chetanbhagat.Images.ImagesPojo
import com.example.chetanbhagat.Quotes.QuotesPojo
import com.example.chetanbhagat.R

class ImagesAdapter (context: Context, private val ImagePojo: ImagesPojo) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val Imagesview : ImageView = itemView.findViewById(R.id.pic)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.images_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Images = ImagePojo[position]
        Glide.with(holder.itemView)
            .load(Images.image) // Load image from URL
            .into(holder.Imagesview) // Set image into ImageViewe

    }

    override fun getItemCount(): Int {
        return  ImagePojo.size
    }
}