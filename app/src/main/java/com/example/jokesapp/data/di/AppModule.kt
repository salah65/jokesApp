package com.example.jokesapp.data.di

import android.content.Context
import com.example.jokesapp.app.MyApplication
import com.example.jokesapp.data.gateways.PreferencesGateway
import com.example.jokesapp.data.gateways.ServerGateway
import com.example.jokesapp.data.gateways.ServerGatewayImplementer
import com.example.jokesapp.data.network.Endpoints
import com.example.jokesapp.data.repositoryImp.JokesRepositoryImp
import com.example.jokesapp.data.repositoryImp.SessionRepositoryImp
import com.example.jokesapp.domain.repository.JokeRepo
import com.example.jokesapp.domain.repository.SessionRepo
import com.example.jokesapp.domain.useCases.RequestJokesUseCase
import com.example.jokesapp.domain.useCases.SessionCountUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication = app as MyApplication



    @Singleton
    @Provides
    fun provideServerGateWay(api: Endpoints): ServerGateway =
        ServerGatewayImplementer(api)

    @Singleton
    @Provides
    fun provideJokesRepository(server: ServerGateway): JokeRepo = JokesRepositoryImp(server)

    @Singleton
    @Provides
    fun provideRequestJokeUseCase(repo: JokeRepo): RequestJokesUseCase = RequestJokesUseCase(repo)

    @Singleton
    @Provides
    fun providePreferenceGatWay(application: MyApplication): PreferencesGateway =
        PreferencesGateway(application)

    @Provides
    @Singleton
    fun provideSessionRepository(preferencesGateway: PreferencesGateway): SessionRepo =
        SessionRepositoryImp(preferencesGateway)

    @Singleton
    @Provides
    fun provideSessionCountUseCase(repo: SessionRepo): SessionCountUseCase =
        SessionCountUseCase(repo)


}