package pokemon.data.mypokemon.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pokemon.abstraction.mypokemon.MyPokemonRemoteDataSource
import pokemon.abstraction.mypokemon.MyPokemonRepository
import pokemon.data.mypokemon.remote.MyPokemonRemoteDataSourceImpl
import pokemon.data.mypokemon.repository.MyPokemonRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class BindActivityRetainedComponent {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRepository(myPokemonRepositoryImpl: MyPokemonRepositoryImpl): MyPokemonRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindRemote(myPokemonRemoteDataSourceImpl: MyPokemonRemoteDataSourceImpl): MyPokemonRemoteDataSource
}