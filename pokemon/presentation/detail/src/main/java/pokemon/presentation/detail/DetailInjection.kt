package pokemon.presentation.detail

import base.model.Route
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import pokemon.hub.DetailContract
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailInjection {

    @Singleton
    @Provides
    @IntoSet
    fun provideDetail(): Route = DetailContract(DetailActivity::class)
}