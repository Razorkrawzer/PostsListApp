package life.league.challenge.kotlin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import life.league.challenge.kotlin.data.api.Api
import life.league.challenge.kotlin.data.repository.UsersRepositoryImpl
import life.league.challenge.kotlin.domain.repository.UsersRepository
import life.league.challenge.kotlin.util.SharedPrefs
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(api: Api, sharedPrefs: SharedPrefs): UsersRepository{
        return UsersRepositoryImpl(api, sharedPrefs)
    }
}