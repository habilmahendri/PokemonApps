package com.pokemonaps

import base.model.BuildFlavor
import base.model.Route
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
@InstallIn(SingletonComponent::class)
object AppInjection {

    @Provides
    @Singleton
    fun provideMapRoute(set: Set<@JvmSuppressWildcards Route>): HashMap<KClass<out Route>, Route> {
        val map = HashMap<KClass<out Route>, Route>()
        set.forEach {
            val kClass = it::class
            map[kClass] = it
        }
        return map
    }

    @Singleton
    @Provides
    fun provideBuildFlavor() = when (BuildConfig.FLAVOR) {
        STAGING -> BuildFlavor.STAGING
        PRODUCTION -> BuildFlavor.PROD
        else -> BuildFlavor.DEV
    }

    private const val STAGING = "staging"
    private const val PRODUCTION = "prod"

}