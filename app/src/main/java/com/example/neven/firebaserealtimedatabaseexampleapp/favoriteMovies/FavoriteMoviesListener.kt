package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies

import com.example.neven.firebaserealtimedatabaseexampleapp.base.BaseListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

interface FavoriteMoviesListener : BaseListener {

    fun onPostReviewSuccess()
    fun onMovieReviewLoaded(movieReview: MovieReview)
    fun onFavoriteMoviesLoaded(query: Query)

}