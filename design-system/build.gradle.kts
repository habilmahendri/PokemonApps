plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "pokemon.design_system"
    compileSdk = rootProject.ext.get("compileSDK") as Int

    defaultConfig {
        minSdk = rootProject.ext.get("minSdk") as Int
        targetSdk = rootProject.ext.get("targetSdk") as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.ext.get("composeCompiler") as String
    }
}

dependencies {
    api("io.coil-kt:coil-compose:2.2.1")
    api("androidx.compose.compiler:compiler:1.4.6")
    // Tooling support (Previews, etc.)
    api("androidx.compose.ui:ui-tooling:1.3.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    api("androidx.compose.foundation:foundation:1.3.1")
    api("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    api("androidx.compose.material:material:1.4.3")
    api(project(":android-base:model"))
    api("androidx.activity:activity-compose:1.6.1")
    api("androidx.fragment:fragment-ktx:1.5.4")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.languageVersion = "1.9"
}
