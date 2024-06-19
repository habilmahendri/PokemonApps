package pokemon.data.mypokemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pokemon.data.mypokemon.network.MyPokemonService
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProvideActivityRetainedComponent {

    @Provides
    @ActivityRetainedScoped
    fun provideHomeService(retrofit: Retrofit): MyPokemonService = retrofit.create(MyPokemonService::class.java)
}
