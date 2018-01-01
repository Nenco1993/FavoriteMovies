package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.neven.firebaserealtimedatabaseexampleapp.R
import com.example.neven.firebaserealtimedatabaseexampleapp.addMovie.Movie
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


import kotlinx.android.synthetic.main.list_item_favorite_movie.view.*

/**
 * Created by Neven on 31-Dec-17.
 */
class FavoriteMoviesAdapter(options: FirebaseRecyclerOptions<Movie>, private val onMovieClickListener: OnMovieClickListener) : FirebaseRecyclerAdapter<Movie, FavoriteMoviesViewHolder>(options) {

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int, singleMovie: Movie) {
        holder.itemView?.tvFavoriteMoviesTitle?.text = singleMovie.title
        val firstPart = holder.itemView?.context?.getString(R.string.picture_url_first_part)
        val pictureID: String? = singleMovie.posterPath
        val pictureURL = firstPart + pictureID
        Glide
                .with(holder.itemView?.context)
                .load(pictureURL)
                .into(holder.itemView?.ivFavoritesMoviesPicture)

        holder.itemView?.ivFavoriteMoviesAddReview?.setOnClickListener {
            onMovieClickListener.onAddReviewClicked(singleMovie)
        }
        holder.itemView?.setOnLongClickListener {
            onMovieClickListener.onMovieLongClicked(singleMovie, holder.itemView)
            true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FavoriteMoviesViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_favorite_movie, parent, false)
        return FavoriteMoviesViewHolder(view)
    }

    override fun onDataChanged() {
        super.onDataChanged()
        if (itemCount == 0) {
            onMovieClickListener.onDatabaseEmpty()
        }
    }

    interface OnMovieClickListener {
        fun onAddReviewClicked(movie: Movie)
        fun onMovieLongClicked(movie: Movie, view: View)
        fun onDatabaseEmpty()
    }
}