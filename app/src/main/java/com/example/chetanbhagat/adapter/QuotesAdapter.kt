package com.example.chetanbhagat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chetanbhagat.Quotes.QuotesPojo
import com.example.chetanbhagat.R
import com.example.chetanbhagat.Videos.VideosPojoItem

class QuotesAdapter(context: Context, private val QuotesPojo: QuotesPojo) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val Quotestxt : TextView = itemView.findViewById(R.id.textview)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quotes_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Quotes = QuotesPojo[position]
        holder.Quotestxt.text = Quotes.text_quote

    }

    override fun getItemCount(): Int {
        return  QuotesPojo.size
    }
}
