package pokemon.data.detail.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pokemon.abstraction.detail.DetailRemoteDataSource
import pokemon.abstraction.detail.DetailRepository
import pokemon.data.detail.network.remote.DetailRemoteDataSourceImpl
import pokemon.data.detail.network.repository.DetailRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class BindActivityRetainedComponent {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRemote(detailRemoteDataSourceImpl: DetailRemoteDataSourceImpl): DetailRemoteDataSource
}
