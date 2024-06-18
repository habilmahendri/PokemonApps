package pokemon.data.detail.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pokemon.abstraction.detail.DetailRepository
import pokemon.usecase.detail.CatchPokemonUseCase
import pokemon.usecase.detail.GetDetailPokemonUseCase

@Module
@InstallIn(ViewModelComponent::class)
object ProvideViewModelComponent {

    @Provides
    @ViewModelScoped
    fun provideGetPokemon(
        repository: DetailRepository,
    ) = GetDetailPokemonUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideCatchPokemon(
        repository: DetailRepository,
    ) = CatchPokemonUseCase(repository)



}