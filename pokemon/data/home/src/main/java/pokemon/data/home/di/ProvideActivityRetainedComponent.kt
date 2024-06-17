package pokemon.data.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pokemon.data.home.network.HomeService
import retrofit2.Retrofit


@Module
@InstallIn(ActivityRetainedComponent::class)
object ProvideActivityRetainedComponent {

    @Provides
    @ActivityRetainedScoped
    fun provideHomeService(retrofit: Retrofit): HomeService = retrofit.create(HomeService::class.java)
}
