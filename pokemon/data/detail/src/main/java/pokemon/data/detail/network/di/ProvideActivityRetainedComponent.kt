package pokemon.data.detail.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pokemon.data.detail.network.DetailService
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProvideActivityRetainedComponent {

    @Provides
    @ActivityRetainedScoped
    fun provideDetailService(retrofit: Retrofit): DetailService = retrofit.create(DetailService::class.java)
}