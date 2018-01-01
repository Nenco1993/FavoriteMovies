package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies.FavoriteMoviesConstants
import com.example.neven.firebaserealtimedatabaseexampleapp.network.RestAPI
import com.example.neven.firebaserealtimedatabaseexampleapp.utils.DisposingObserver
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddMovieInteractor @Inject constructor(private val api: RestAPI, private val database: FirebaseDatabase) : AddMovieContract.Interactor {

    lateinit var observableMovies: Observable<MovieData>
    private val dbMovies = database.getReference(FavoriteMoviesConstants.KEY_FIREBASE_DATABASE_REF_MOVIES)
    private val listMovieIds = ArrayList<String>()
    private var counterSavedMovies = 0

    override fun downloadData(listener: AddMovieListener) {
        observableMovies = api.getMovies().cache()
        observableMovies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposingObserver<MovieData>() {
                    override fun onNext(item: MovieData) {
                        super.onNext(item)
                        listener.onMovieDataSuccess(item.movies!!)
                    }

                    override fun onError(t: Throwable?) {
                        super.onError(t)
                        t?.printStackTrace()
                    }
                })
    }

    override fun postFavoriteMovies(listMovies: List<Movie>, listener: AddMovieListener) {
        counterSavedMovies = 0
        val numberOfMovies: Int = listMovies.size
        listMovies.forEach {
            saveNewMovie(it, listener, numberOfMovies)
        }
    }

    fun saveNewMovie(movie: Movie, listener: AddMovieListener, numberOfMovies: Int) {
        val newMovie = Movie(
                movie.voteCount,
                movie.id,
                movie.video,
                movie.voteAverage,
                movie.title,
                movie.popularity,
                movie.posterPath,
                movie.originalLanguage,
                movie.originalTitle,
                movie.genreIds,
                movie.backdropPath,
                movie.adult,
                movie.overview,
                movie.releaseDate,
                movie.isFavorite
        )
        dbMovies.child(movie.id.toString()).setValue(newMovie).addOnCompleteListener {
            listMovieIds.add(movie.id.toString())
            counterSavedMovies++
            if (counterSavedMovies == numberOfMovies) {
                listener.onPostFavoriteMoviesSuccess()
            }
        }
    }
}