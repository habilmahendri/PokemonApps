package pokemon.data.mypokemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pokemon.abstraction.mypokemon.MyPokemonRepository
import pokemon.usecase.mypokemon.GetMyPokemonsUseCase

@Module
@InstallIn(ViewModelComponent::class)
internal object ProvideViewModelComponent {

    @Provides
    @ViewModelScoped
    fun provideGetPokemon(
        repository: MyPokemonRepository,
    ) = GetMyPokemonsUseCase(repository)
}