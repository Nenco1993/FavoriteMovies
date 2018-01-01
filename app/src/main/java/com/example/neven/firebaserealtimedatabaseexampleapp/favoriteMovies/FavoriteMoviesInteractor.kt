package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies


import com.example.neven.firebaserealtimedatabaseexampleapp.addMovie.Movie
import com.google.firebase.database.*
import javax.inject.Inject

/**
 * Created by Neven on 28.9.2017..
 */
class FavoriteMoviesInteractor @Inject constructor(database: FirebaseDatabase) : FavoriteMoviesContract.Interactor {

    private val dbMovies = database.getReference(FavoriteMoviesConstants.KEY_FIREBASE_DATABASE_REF_MOVIES)
    private val dbReviews = database.getReference(FavoriteMoviesConstants.KEY_FIREBASE_DATABASE_REF_REVIEWS)
    private var valueEventReviewListener: ValueEventListener? = null

    override fun getFavoriteMovies(listener: FavoriteMoviesListener) {
        val query: Query = dbMovies
        listener.onFavoriteMoviesLoaded(query)
    }

    override fun postReview(movieReview: MovieReview, listener: FavoriteMoviesListener) {
        dbReviews.child(movieReview.reviewID.toString()).setValue(movieReview).addOnCompleteListener {
            listener.onPostReviewSuccess()
        }
    }

    override fun getReview(movieReview: MovieReview, listener: FavoriteMoviesListener) {
        valueEventReviewListener = dbReviews.child(movieReview.reviewID.toString()).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val review: MovieReview? = p0?.getValue(MovieReview::class.java)
                if (review != null) {
                    listener.onMovieReviewLoaded(review)
                }
            }
        })

    }

    override fun removeReviewListener() {
        dbReviews.removeEventListener(valueEventReviewListener)
    }

    override fun deleteFavoriteMovie(movie: Movie) {
        deleteMovie(dbMovies, movie)
        deleteMovie(dbReviews, movie)
    }

    fun deleteMovie(dbRef: DatabaseReference, movie: Movie) {
        dbRef.child(movie.id.toString()).setValue(null)
    }


}
