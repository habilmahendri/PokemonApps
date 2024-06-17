object Dependencies {

    object Hilt{
        private const val hiltVersion = "2.46.1"
        const val PLUGIN = "dagger.hilt.android.plugin"
        const val DEPENDENCY = "com.google.dagger:hilt-android:$hiltVersion"
        const val KAPT = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    }

    object Room{
        private const val roomVersion = "2.4.3"
        const val DEPENDENCY  =  "androidx.room:room-runtime:$roomVersion"
        const val KAPT  =  "androidx.room:room-compiler:$roomVersion"
        const val COROUTINE = "androidx.room:room-ktx:$roomVersion"
    }

    object Coroutines{
        private const val version = "1.6.3"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${version}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${version}"
    }
}