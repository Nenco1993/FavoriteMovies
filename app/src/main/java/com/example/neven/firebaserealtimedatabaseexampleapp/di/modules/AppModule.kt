package com.example.neven.firebaserealtimedatabaseexampleapp.di.modules

import com.google.firebase.database.FirebaseDatabase


import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

  /*  @Provides
    @Singleton
    fun provideDatabaseManager(database: FirebaseDatabase): DatabaseManager {
        return DatabaseManager(database)
    }*/
}