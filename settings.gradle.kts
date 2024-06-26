pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PokemonApps"
include(":app")
include(":android-base")
include(":android-base:usecase")
include(":android-base:hub")
include(":android-base:model")
include(":android-base:presentation")
includeBuild("build-script")
include(":android-base:data")
include(":android-base:abstraction")
include(":pokemon:abstraction:base")
include(":pokemon:usecase:base")
include(":pokemon:model:base")
include(":pokemon:presentation:base")
include(":pokemon:data:base")
include(":pokemon:data:core")
include(":pokemon:model:core")
include(":pokemon:abstraction:home")
include(":pokemon:presentation:home")
include(":pokemon:data:home")
include(":pokemon:model:home")
include(":pokemon:usecase:home")
include(":pokemon:presentation:core")
include(":design-system")
include(":pokemon:presentation:detail")
include(":pokemon:model:detail")
include(":pokemon:usecase:detail")
include(":pokemon:abstraction:detail")
include(":pokemon:data:detail")
include(":pokemon:hub")
include(":pokemon:presentation:mypokemon")
include(":pokemon:model:mypokemon")
include(":pokemon:usecase:mypokemon")
include(":pokemon:abstraction:mypokemon")
include(":pokemon:data:mypokemon")
