package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies

import com.example.neven.firebaserealtimedatabaseexampleapp.di.scopes.ActivityScope
import dagger.Subcomponent

/**
 * Created by Neven on 28.9.2017..
 */
@ActivityScope
@Subcomponent(modules = arrayOf(FavoriteMoviesModule::class))
interface FavoriteMoviesComponent {

    fun inject(activity: FavoriteMoviesActivity)

}