buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("com.google.gms:google-services:4.3.14")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    }
}
plugins{
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version ("1.8.20") apply false
    id("builderplugin")
}
allprojects{
    rootProject.extra.apply {
        set("compileSDK", AppConfig.compileSdk)
        set("minSdk", AppConfig.minSdk)
        set("targetSdk", AppConfig.targetSdk)
        set("hiltDependency", Dependencies.Hilt.DEPENDENCY)
        set("hiltKapt", Dependencies.Hilt.KAPT)
        set("coroutineCore", Dependencies.Coroutines.core)
        set("coroutineAndroid", Dependencies.Coroutines.android)
        set("roomDependency", Dependencies.Room.DEPENDENCY)
        set("roomKapt", Dependencies.Room.KAPT)
        set("roomDependency", Dependencies.Room.DEPENDENCY)
        set("roomCoroutine", Dependencies.Room.COROUTINE)
        set("composeCompiler", AppConfig.composeCompilerVersion)
    }
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}