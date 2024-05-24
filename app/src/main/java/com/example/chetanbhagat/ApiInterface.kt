package com.example.chetanbhagat

import com.example.chetanbhagat.Books.BooksPojo
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/v3/chetan_bhagat/book_link")
    fun Books(): Call<BooksPojo>



    @GET("/v3/chetan_bhagat/text_quotes")
    fun Quotes (): Call<BooksPojo>

}