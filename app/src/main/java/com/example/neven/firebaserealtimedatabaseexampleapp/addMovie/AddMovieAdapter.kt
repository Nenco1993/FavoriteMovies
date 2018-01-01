package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.neven.firebaserealtimedatabaseexampleapp.R
import kotlinx.android.synthetic.main.list_item_add_movie.view.*
import org.jetbrains.anko.image

class AddMovieAdapter(private val listMovies: List<Movie>, private val onStarClickListener: OnStarClickListener) : RecyclerView.Adapter<AddMovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AddMovieViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_add_movie, parent, false)

        return AddMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddMovieViewHolder?, position: Int) {
        val singleMovie: Movie = listMovies[position]

        val firstPart = holder?.itemView?.context?.getString(R.string.picture_url_first_part)
        val pictureID: String = singleMovie.posterPath!!
        val pictureURL = firstPart + pictureID

        Glide
                .with(holder?.itemView?.context)
                .load(pictureURL)
                .into(holder?.itemView?.ivAddMoviePicture)

        holder?.itemView?.tvAddMovieTitle?.text = singleMovie.title

        val ivStarOff = ContextCompat.getDrawable(holder!!.itemView!!.context, android.R.drawable.star_off)
        val ivStarOn = ContextCompat.getDrawable(holder.itemView!!.context, android.R.drawable.star_on)

        if (singleMovie.isFavorite) {
            holder.itemView.ivAddMovieToFavorites?.image = ivStarOn
        } else {
            holder.itemView.ivAddMovieToFavorites?.image = ivStarOff
        }

        holder.itemView.ivAddMovieToFavorites?.setOnClickListener {

            if (!singleMovie.isFavorite) {
                holder.itemView.ivAddMovieToFavorites.image = ivStarOn
                singleMovie.isFavorite = true
                onStarClickListener.onStarClicked(true, singleMovie)
            } else {
                holder.itemView.ivAddMovieToFavorites.image = ivStarOff
                singleMovie.isFavorite = false
                onStarClickListener.onStarClicked(false, singleMovie)
            }
        }
    }


    override fun getItemCount(): Int {
        return listMovies.size
    }

    interface OnStarClickListener {
        fun onStarClicked(isFavorite: Boolean, singleMovie: Movie)
    }
}