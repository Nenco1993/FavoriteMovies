package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import com.example.neven.firebaserealtimedatabaseexampleapp.di.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(AddMovieModule::class))
interface AddMovieComponent {

    fun inject(activity: AddMovieActivity)

}