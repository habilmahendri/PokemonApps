plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.pokemonaps"
    compileSdk = rootProject.ext.get("compileSDK") as Int

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    flavorDimensions.add("default")

    productFlavors {
        create("dev") {
            applicationId = AppConfig.applicationId + ".develop"
        }
        create("staging") {
            applicationId = AppConfig.applicationId + ".staging"
        }
        create("prod") {
            applicationId = AppConfig.applicationId
        }
    }
}

dependencies {
    api(rootProject.ext.get("hiltDependency") as String)
    kapt(rootProject.ext.get("hiltKapt") as String)

    rootProject.childProjects["pokemon"]?.childProjects?.get("presentation")?.childProjects?.forEach {
        api(project(it.value.path))
    }

    rootProject.childProjects["pokemon"]?.childProjects?.get("data")?.childProjects?.forEach {
        implementation(project(it.value.path))
    }
    implementation(project(":pokemon:model:core"))
    implementation(project(mapOf("path" to ":android-base:hub")))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("com.google.android.material:material:1.6.1")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}