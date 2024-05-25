package com.example.chetanbhagat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chetanbhagat.R
import com.example.chetanbhagat.Videos.VideosPojo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideosAdapter(private val context: Context, private val videosPojo: VideosPojo) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoTitle: TextView = itemView.findViewById(R.id.title_text_view)
        val youTubePlayerView: YouTubePlayerView = itemView.findViewById(R.id.youtube_player_view)
        var youTubePlayer: YouTubePlayer? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.videos_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videosPojo[position]
        holder.videoTitle.text = video.title

        // Initialize YouTubePlayer
        holder.youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                holder.youTubePlayer = youTubePlayer
                youTubePlayer.cueVideo(video.video_url, 0f)
            }
        })
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        // Release the YouTubePlayer to prevent memory leaks
        holder.youTubePlayer?.pause()
        holder.youTubePlayer = null
    }

    override fun getItemCount(): Int {
        return videosPojo.size
    }
}
