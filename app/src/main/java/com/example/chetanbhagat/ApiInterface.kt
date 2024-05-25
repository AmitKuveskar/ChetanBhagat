package com.example.chetanbhagat

import com.example.chetanbhagat.Books.BooksPojo
import com.example.chetanbhagat.Images.ImagesPojo
import com.example.chetanbhagat.MotivationalStories.MotivationalPojo
import com.example.chetanbhagat.Quotes.QuotesPojo
import com.example.chetanbhagat.Videos.VideosPojo
import com.example.chetanbhagat.Videos.VideosPojoItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/v3/chetan_bhagat/book_link")
    fun Books(): Call<BooksPojo>

    @GET("/v3/chetan_bhagat/text_quotes")
    fun Quotes (): Call<QuotesPojo>

    @GET("/v3/chetan_bhagat/text_stories")
    fun Motivational(): Call<MotivationalPojo>

    @GET("/v3/chetan_bhagat/image_quotes")
    fun Images(): Call<ImagesPojo>

    @GET("/v3/chetan_bhagat/video_stories")
    fun Videos(): Call<VideosPojo>
}