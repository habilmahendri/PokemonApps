plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}
android {
    namespace = "base.presentation"
    compileSdk = rootProject.ext.get("compileSDK") as Int
    defaultConfig {
        minSdk = rootProject.ext.get("minSdk") as Int
        targetSdk = rootProject.ext.get("targetSdk") as Int
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
}
dependencies {
    api(rootProject.ext.get("hiltDependency") as String)
    kapt(rootProject.ext.get("hiltKapt") as String)

    api("androidx.appcompat:appcompat:1.5.1")
    api("androidx.compose.ui:ui:1.3.1")
    api("androidx.hilt:hilt-navigation-compose:1.0.0")
    api("androidx.activity:activity-compose:1.6.1")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    api("androidx.navigation:navigation-compose:2.5.3")
    api("androidx.compose.ui:ui-tooling-preview:1.3.1")
    api("androidx.compose.compiler:compiler:1.3.2")
    // Tooling support (Previews, etc.)
    api("androidx.compose.ui:ui-tooling:1.3.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    api("androidx.compose.foundation:foundation:1.3.1")
    api("androidx.constraintlayout:constraintlayout-compose:1.0.1")



    api ("androidx.activity:activity-ktx:1.6.1")
    api ("androidx.fragment:fragment-ktx:1.5.4")

    api (project(":android-base:usecase"))

    // Material Design
    api("androidx.compose.material:material:1.2.1")
    api("com.google.accompanist:accompanist-pager:0.23.1")
    api("com.google.accompanist:accompanist-pager-indicators:0.23.1")
    api("com.google.accompanist:accompanist-navigation-animation:0.23.1")
    // Material design icons
    api("androidx.compose.material:material-icons-core:1.2.1")
    api("androidx.compose.material:material-icons-extended:1.2.1")
    // Integration with observables
    api("androidx.compose.runtime:runtime-livedata:1.2.1")
    api("androidx.customview:customview:1.2.0-alpha02")
    api("androidx.customview:customview-poolingcontainer:1.0.0")
    api("com.google.android.material:material:1.6.1")
}
