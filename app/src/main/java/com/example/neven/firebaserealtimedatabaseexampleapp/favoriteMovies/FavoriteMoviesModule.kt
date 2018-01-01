package com.example.neven.firebaserealtimedatabaseexampleapp.favoriteMovies

import com.example.neven.firebaserealtimedatabaseexampleapp.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by Neven on 28.9.2017..
 */

@Module
class FavoriteMoviesModule {

    @Provides
    @ActivityScope
    fun providePresenter(presenter: FavoriteMoviesPresenter): FavoriteMoviesContract.Presenter {
        return presenter
    }

    @Provides
    @ActivityScope
    fun provideInteractor(interactor: FavoriteMoviesInteractor): FavoriteMoviesContract.Interactor {

        return interactor
    }


}