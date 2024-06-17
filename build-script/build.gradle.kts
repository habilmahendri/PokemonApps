plugins {
    `kotlin-dsl`
    `kotlin-dsl-base`
    `java-gradle-plugin`
    `embedded-kotlin`
    `kotlin-dsl-precompiled-script-plugins`
    kotlin("jvm") version "1.8.20"
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    maven("https://maven.google.com")
    maven("https://jitpack.io")
    jcenter()
}


dependencies {
//    implementation(kotlin("stdlib"))
//    implementation("com.android.tools.build:gradle:4.0.0")
    api ("org.jetbrains.kotlin:kotlin-stdlib:1.8.20")
    api ("com.android.tools.build:gradle:7.3.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    testImplementation("junit:junit:4.13")
}


gradlePlugin{
    plugins{
        register("builderplugin"){
            id = "builderplugin"
            implementationClass = "BuilderPlugin"
        }
    }
}


val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
    languageVersion = "1.5"
}

val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}