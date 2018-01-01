package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies

import com.example.neven.firebaserealtimedatabaseexampleapp.addMovie.Movie
import com.example.neven.firebaserealtimedatabaseexampleapp.base.BasePresenter
import com.example.neven.firebaserealtimedatabaseexampleapp.base.BaseView
import com.google.firebase.database.Query

/**
 * Created by Neven on 28.9.2017..
 */
class FavoriteMoviesContract {

    interface View : BaseView<Presenter> {
        fun showReview(review: MovieReview, isReviewAvailable: Boolean)
        fun onReviewSaved()
        fun showFavoriteMovies(query: Query)
    }

    interface Presenter : BasePresenter<View> {
        fun saveReview(movieReview: MovieReview)
        fun loadReview(movieReview: MovieReview)
        fun loadFavoriteMovies()
        fun removeFavoriteMovie(movie: Movie)
    }

    interface Interactor {
        fun postReview(movieReview: MovieReview, listener: FavoriteMoviesListener)
        fun getReview(movieReview: MovieReview, listener: FavoriteMoviesListener)
        fun getFavoriteMovies(listener: FavoriteMoviesListener)
        fun deleteFavoriteMovie(movie: Movie)

    }
}