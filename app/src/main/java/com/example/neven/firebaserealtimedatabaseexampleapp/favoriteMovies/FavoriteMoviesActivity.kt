package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import com.example.neven.firebaserealtimedatabaseexampleapp.R
import com.example.neven.firebaserealtimedatabaseexampleapp.addMovie.Movie
import com.example.neven.firebaserealtimedatabaseexampleapp.base.BaseActivity
import com.example.neven.firebaserealtimedatabaseexampleapp.di.components.AppComponent
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.content_favorite_movies.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class FavoriteMoviesActivity : BaseActivity(), FavoriteMoviesAdapter.OnMovieClickListener, FavoriteMoviesContract.View {

    @Inject
    lateinit var presenter: FavoriteMoviesContract.Presenter

    var movieReview: MovieReview = MovieReview()
    var adapterMovies: FavoriteMoviesAdapter? = null
    var etReview: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = getString(R.string.favorite_movies_toolbar_title)
        setSupportActionBar(toolbar)

        val manager = LinearLayoutManager(this)
        rvFavoriteMovies.layoutManager = manager
        rvFavoriteMovies.itemAnimator = DefaultItemAnimator()
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
        presenter.loadFavoriteMovies()
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun onStop() {
        super.onStop()
        adapterMovies?.stopListening()
    }

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.plus(FavoriteMoviesModule()).inject(this)
    }

    override fun showReview(review: MovieReview, isReviewAvailable: Boolean) {
        showAddReviewDialog(review, isReviewAvailable)
    }

    override fun onAddReviewClicked(movie: Movie) {
        movieReview.reviewID = movie.id
        presenter.loadReview(movieReview)
        showAddReviewDialog(movieReview, false)
    }

    override fun onMovieLongClicked(movie: Movie, view: View) {
        showPopupMenu(view, movie)
    }

    override fun showFailureMessage() {
        toast(getString(R.string.error_failed_to_retrieve_data))
    }

    override fun onReviewSaved() {
        toast(getString(R.string.favorite_movies_dialog_add_review_button_saved))
    }

    override fun showFavoriteMovies(query: Query) {
        val options = FirebaseRecyclerOptions.Builder<Movie>()
                .setQuery(query, Movie::class.java)
                .build()
        adapterMovies = FavoriteMoviesAdapter(options, this)
        rvFavoriteMovies.adapter = adapterMovies
        adapterMovies?.startListening()
    }

    override fun onDatabaseEmpty() {
        toast(getString(R.string.favorite_movies_empty))
    }

    fun showAddReviewDialog(movieReview: MovieReview, isReviewAvailable: Boolean) {
        if (isReviewAvailable) {
            etReview?.setText(movieReview.reviewText)
        } else {
            val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflater: LayoutInflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_favorite_movies_add_review, null)
            dialog.setView(dialogView)
            dialog.setTitle(getString(R.string.favorite_movies_dialog_add_review_title))
            dialog.setMessage(getString(R.string.favorite_movies_dialog_add_review_message))
            etReview = dialogView.findViewById<EditText>(R.id.etFavoriteMoviesReviewText)
            dialog.setPositiveButton(getString(R.string.favorite_movies_dialog_add_review_button_save)) { p0, p1 ->
                movieReview.reviewText = etReview?.text.toString()
                presenter.saveReview(movieReview)
            }
            dialog.setNegativeButton(getString(R.string.favorite_movies_dialog_add_review_button_cancel)) { p0, p1 ->
            }
            dialog.create().show()
        }
    }

    fun showPopupMenu(view: View, movie: Movie) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_popup, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.actionDeleteMovie -> presenter.removeFavoriteMovie(movie)
            }
            true
        }
        popupMenu.show()
    }
}






