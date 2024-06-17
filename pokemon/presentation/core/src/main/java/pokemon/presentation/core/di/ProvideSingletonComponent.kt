package pokemon.presentation.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pokemon.presentation.base.PokemonUseCaseErrorMapper
import pokemon.presentation.core.PokemonUseCaseErrorMapperImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProvideSingletonComponent {

    @Singleton
    @Provides
    fun provideErrorMapper() = PokemonUseCaseErrorMapperImpl() as PokemonUseCaseErrorMapper
}