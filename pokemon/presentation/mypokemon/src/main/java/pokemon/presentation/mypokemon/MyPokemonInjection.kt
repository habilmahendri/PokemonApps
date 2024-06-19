package pokemon.presentation.mypokemon

import base.model.Route
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import pokemon.hub.MyPokemonContract
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyPokemonInjection {

    @Singleton
    @Provides
    @IntoSet
    fun provideDetail(): Route = MyPokemonContract(MyPokemonActivity::class)
}