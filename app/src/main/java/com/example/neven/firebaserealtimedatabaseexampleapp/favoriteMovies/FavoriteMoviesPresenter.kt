package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies

import com.example.neven.firebaserealtimedatabaseexampleapp.addMovie.Movie
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

/**
 * Created by Neven on 28.9.2017..
 */
class FavoriteMoviesPresenter @Inject constructor(val interactor: FavoriteMoviesInteractor) : FavoriteMoviesContract.Presenter, FavoriteMoviesListener {

    private var view: FavoriteMoviesContract.View? = null


    override fun takeView(view: FavoriteMoviesContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun loadReview(movieReview: MovieReview) {
        interactor.getReview(movieReview, this)
    }

    override fun saveReview(movieReview: MovieReview) {
        interactor.postReview(movieReview, this)
    }

    override fun removeReviewListener() {
        interactor.removeReviewListener()
    }

    override fun onPostReviewSuccess() {
        view?.onReviewSaved()
    }

    override fun onMovieReviewLoaded(movieReview: MovieReview) {
        view?.showReview(movieReview, true)
    }

    override fun removeFavoriteMovie(movie: Movie) {
        interactor.deleteFavoriteMovie(movie)
    }

    override fun onFailure() {
        view?.showFailureMessage()
    }

    override fun unsubscribe() {
        // got nothing to unsubscribe
    }

    override fun loadFavoriteMovies() {
        interactor.getFavoriteMovies(this)
    }

    override fun onFavoriteMoviesLoaded(query: Query) {
        view?.showFavoriteMovies(query)
    }
}
