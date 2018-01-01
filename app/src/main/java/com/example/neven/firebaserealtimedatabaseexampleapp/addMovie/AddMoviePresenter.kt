package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import com.example.neven.firebaserealtimedatabaseexampleapp.utils.DisposableManager
import javax.inject.Inject

class AddMoviePresenter @Inject constructor(val interactor: AddMovieContract.Interactor) : AddMovieContract.Presenter, AddMovieListener {

    var view: AddMovieContract.View? = null


    override fun loadData() {
        interactor.downloadData(this)
    }

    override fun saveFavorites(listMovies: List<Movie>) {
        if (listMovies.isEmpty()) {
            view?.showSavedMovies()
        } else {
            interactor.postFavoriteMovies(listMovies, this)
        }
    }

    override fun onPostFavoriteMoviesSuccess() {
        view?.showSavedMovies()
    }

    override fun takeView(view: AddMovieContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun onMovieDataSuccess(listMovies: List<Movie>) {
        view?.showData(listMovies)
    }

    override fun onFailure() {

        view?.showFailureMessage()

    }

    override fun unsubscribe() {
        DisposableManager.instance.clear()
    }
}