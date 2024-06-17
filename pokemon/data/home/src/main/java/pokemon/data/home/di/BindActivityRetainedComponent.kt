package pokemon.data.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pokemon.abstraction.home.HomeRemoteDataSource
import pokemon.abstraction.home.HomeRepository
import pokemon.data.home.remote.HomeRemoteDataSourceImpl
import pokemon.data.home.repository.HomeRepositoryImpl


@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class BindActivityRetainedComponent {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRemote(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource
}
