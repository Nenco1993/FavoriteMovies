package com.example.neven.firebaserealtimedatabaseexampleapp.addMovie

import com.example.neven.firebaserealtimedatabaseexampleapp.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class AddMovieModule {

    @Provides
    @ActivityScope
    fun providePresenter(presenter: AddMoviePresenter): AddMovieContract.Presenter {
        return presenter
    }

    @Provides
    @ActivityScope
    fun provideInteractor(interactor: AddMovieInteractor): AddMovieContract.Interactor {
        return interactor
    }

}