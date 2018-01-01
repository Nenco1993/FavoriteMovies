package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieData {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("results")
    @Expose
    var movies: List<Movie>? = null

}
