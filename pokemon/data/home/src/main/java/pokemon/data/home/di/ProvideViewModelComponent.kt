package pokemon.data.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pokemon.abstraction.home.HomeRepository
import pokemon.usecase.home.GetPokemonsUseCase

@Module
@InstallIn(ViewModelComponent::class)
internal object ProvideViewModelComponent {

    @Provides
    @ViewModelScoped
    fun provideGetPokemon(
        repository: HomeRepository,
    ) = GetPokemonsUseCase(repository)
}