package com.example.neven.firebaserealtimedatabaseexampleapp.network

import com.example.neven.firebaserealtimedatabaseexampleapp.addMovie.MovieData
import io.reactivex.Observable
import retrofit2.http.GET

interface RestAPI {

    @GET("discover/movie?api_key=8726f0b2dce5c4141686ea1c46d6481b&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getMovies(): Observable<MovieData>


}