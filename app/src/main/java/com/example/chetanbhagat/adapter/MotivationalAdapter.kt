package com.example.chetanbhagat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chetanbhagat.MotivationalStories.MotivationalPojo
import com.example.chetanbhagat.Quotes.QuotesPojo
import com.example.chetanbhagat.R

class MotivationalAdapter (context: Context, private val MotivationalPojo: MotivationalPojo) :
    RecyclerView.Adapter<MotivationalAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val MOTIVATIONALtxt : TextView = itemView.findViewById(R.id.textview)
        val MotivationalTitle : TextView = itemView.findViewById(R.id.title)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.motivational_row,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Motivational = MotivationalPojo[position]
        holder.MOTIVATIONALtxt.text = Motivational.text_stories
        holder.MotivationalTitle.text = Motivational.title

    }

    override fun getItemCount(): Int {
        return  MotivationalPojo.size
    }
}
