package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import com.example.neven.firebaserealtimedatabaseexampleapp.base.BasePresenter
import com.example.neven.firebaserealtimedatabaseexampleapp.base.BaseView

class AddMovieContract {

    interface View : BaseView<Presenter> {
        fun showData(listMovies: List<Movie>)
        fun showSavedMovies()
    }

    interface Presenter : BasePresenter<View> {
        fun loadData()
        fun saveFavorites(listMovies: List<Movie>)
    }

    interface Interactor {
        fun downloadData(listener: AddMovieListener)
        fun postFavoriteMovies(listMovies: List<Movie>, listener: AddMovieListener)
    }
}