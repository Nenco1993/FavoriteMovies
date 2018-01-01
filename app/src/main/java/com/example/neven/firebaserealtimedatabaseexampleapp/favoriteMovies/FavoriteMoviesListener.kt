package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies

import com.example.neven.firebaserealtimedatabaseexampleapp.base.BaseListener
import com.google.firebase.database.Query

interface FavoriteMoviesListener : BaseListener {

    fun onPostReviewSuccess()
    fun onMovieReviewLoaded(movieReview: MovieReview)
    fun onFavoriteMoviesLoaded(query: Query)

}