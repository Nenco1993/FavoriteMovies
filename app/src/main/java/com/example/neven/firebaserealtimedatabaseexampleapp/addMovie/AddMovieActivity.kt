package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.neven.firebaserealtimedatabaseexampleapp.R
import com.example.neven.firebaserealtimedatabaseexampleapp.base.BaseActivity
import com.example.neven.firebaserealtimedatabaseexampleapp.di.components.AppComponent
import com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies.FavoriteMoviesActivity
import kotlinx.android.synthetic.main.content_add_movie.*

import org.jetbrains.anko.toast
import javax.inject.Inject


class AddMovieActivity : BaseActivity(), AddMovieContract.View, AddMovieAdapter.OnStarClickListener {

    @Inject
    lateinit var presenter: AddMovieContract.Presenter

    private var listFavorites = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = getString(R.string.add_movies_toolbar_title)
        setSupportActionBar(toolbar)

        val manager = LinearLayoutManager(this)

        recyclerView.layoutManager = manager
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onResume() {
        super.onResume()
        if (!listFavorites.isEmpty()) {
            listFavorites.clear()
        }
        presenter.takeView(this)
        presenter.loadData()
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
        presenter.unsubscribe()
    }


    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.plus(AddMovieModule()).inject(this)
    }

    override fun showData(listMovies: List<Movie>) {
        recyclerView.adapter = AddMovieAdapter(listMovies, this)
    }

    override fun showFailureMessage() {
        toast(getString(R.string.error_failed_to_retrieve_data))
    }

    override fun onStarClicked(isFavorite: Boolean, singleMovie: Movie) {
        if (isFavorite) {
            listFavorites.add(singleMovie)
        } else {
            if (!listFavorites.isEmpty()) {
                listFavorites.remove(singleMovie)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_favorites) {
            presenter.saveFavorites(listFavorites)
        } else {
            super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun showSavedMovies() {
        val intent = Intent(baseContext, FavoriteMoviesActivity::class.java)
        startActivity(intent)
    }
}
