package pokemon.presentation.core.di

import android.content.Context
import androidx.fragment.app.FragmentActivity
import base.hub.RouterHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import pokemon.presentation.base.PokemonErrorHandler
import pokemon.presentation.base.PokemonUseCaseErrorMapper
import pokemon.presentation.core.PokemonErrorHandlerImpl

@Module
@InstallIn(ActivityComponent::class)
internal object ProvideActivityComponent {

    @Provides
    @ActivityScoped
    fun provideDefaultErrorHandler(
        @ActivityContext context: Context,
        errorMapper: PokemonUseCaseErrorMapper,
    ): PokemonErrorHandler =
        PokemonErrorHandlerImpl(context as FragmentActivity, errorMapper)

}