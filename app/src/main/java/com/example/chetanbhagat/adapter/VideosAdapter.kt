package com.example.chetanbhagat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chetanbhagat.Quotes.QuotesPojo
import com.example.chetanbhagat.R
import com.example.chetanbhagat.Videos.VideosPojo

class VideosAdapter (context: Context, private val VideosPojo: VideosPojo) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val Videos : TextView = itemView.findViewById(R.id.textview)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quotes_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Videos = VideosPojo[position]
        holder.Videos.text = Videos.video_url

    }

    override fun getItemCount(): Int {
        return  VideosPojo.size
    }
}