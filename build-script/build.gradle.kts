plugins {
    `kotlin-dsl`
    `kotlin-dsl-base`
    `java-gradle-plugin`
    `embedded-kotlin`
    `kotlin-dsl-precompiled-script-plugins`
    kotlin("jvm") version "1.7.10"
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
    api ("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")
    api ("com.android.tools.build:gradle:7.3.0")
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


//val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//    languageVersion = "1.5"
//}
//
//val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
//compileTestKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//}