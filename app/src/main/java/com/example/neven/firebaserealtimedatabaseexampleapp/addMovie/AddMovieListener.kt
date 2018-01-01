package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import com.example.neven.firebaserealtimedatabaseexampleapp.base.BaseListener

interface AddMovieListener : BaseListener {

    fun onMovieDataSuccess(listMovies: List<Movie>)
    fun onPostFavoriteMoviesSuccess()

}