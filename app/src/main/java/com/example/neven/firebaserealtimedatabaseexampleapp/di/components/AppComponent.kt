package com.example.neven.firebaserealtimedatabaseexampleapp.di.components

import com.example.neven.firebaserealtimedatabaseexampleapp.addMovie.AddMovieComponent
import com.example.neven.firebaserealtimedatabaseexampleapp.addMovie.AddMovieModule
import com.example.neven.firebaserealtimedatabaseexampleapp.di.modules.AppModule
import com.example.neven.firebaserealtimedatabaseexampleapp.di.modules.NetModule
import com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies.FavoriteMoviesComponent
import com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies.FavoriteMoviesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {

    fun plus(module: AddMovieModule): AddMovieComponent
    fun plus(module: FavoriteMoviesModule): FavoriteMoviesComponent


}