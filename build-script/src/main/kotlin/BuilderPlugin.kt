import AppConfig.compileSdk
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import java.io.File

@Suppress("UnstableApiUsage")
class BuilderPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.childProjects.values.forEach {
            if (it.name == "pokemon") {
                handleProject(it)
            }
        }
    }

    val mainProjectName = "pokemon"

    private fun handleProject(project: Project) {
        createGeneratedClass(project)
        project.childProjects.values.forEach { moduleType ->
            when (moduleType.name) {
                "abstraction",
                "data",
                "model",
                "presentation",
                "usecase" -> {
                    moduleType.childProjects.values.forEach { feature ->
                        feature.handlePlugins(moduleType.name)
                        feature.beforeEvaluate {
                            feature.configureAndroid(moduleType.name)
                            feature.addDependencies(moduleType.name)
                        }
                    }
                }
            }
        }
    }


    private fun Project.handlePlugins(name: String) {
        plugins.apply {
            apply("com.android.library")
            apply("kotlin-android")
            when (name) {
                "model" -> {
                    apply("kotlin-parcelize")
                }
                "data" -> {
                    apply("kotlin-parcelize")
                    apply("kotlin-kapt")
                    apply(Dependencies.Hilt.PLUGIN)
                }
                "presentation" -> {
                    apply("kotlin-parcelize")
                    apply("kotlin-kapt")
                    apply(Dependencies.Hilt.PLUGIN)
                }
            }
        }
    }


    private fun Project.configureAndroid(name: String) {
        val currentName = this.name
        androidConfiguration {
            namespace = "pokemon.$name.$currentName"
            compileSdk = AppConfig.compileSdk

            defaultConfig {
                minSdk = AppConfig.minSdk
                targetSdk = AppConfig.targetSdk

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
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
            if (name == "presentation") {
                buildFeatures {
                    compose = true
                    viewBinding = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = AppConfig.composeCompilerVersion
                }
            }
        }
    }


    private fun Project.addDependencies(moduleType: String) {
        dependencies {
            when (moduleType) {
                "abstraction" -> {
                    if (name == "base") {
                        api(project(":android-base:abstraction"))
                    } else {
                        abstraction(AbstractionModules.Base, true)
                    }
                    model(findModule())
                }
                "data" -> {
                    if (name == "base") {
                        api(project(":android-base:data"))
                    } else {
                        data(DataModules.Base)
                    }
                    abstraction(findModule(), true)
                    usecase(findModule())
                    model(findModule())
                    add("kapt", Dependencies.Hilt.KAPT)
                    add("api", Dependencies.Hilt.DEPENDENCY)
                    add("kapt", Dependencies.Room.KAPT)
                }
                "model" -> {
                    if (name == "base") {
                        api(project(":android-base:model"))
                    } else {
                        model(ModelModules.Base)
                    }
                }
                "presentation" -> {
                    if (name == "base") {
                        api(project(":android-base:presentation"))
                    } else {
                        presentation(PresentationModules.Base)
                    }
                    usecase(findModule())
                    model(findModule())
                    add("kapt", Dependencies.Hilt.KAPT)
                    add("api", Dependencies.Hilt.DEPENDENCY)
                }
                "usecase" -> {
                    if (name == "base") {
                        api(project(":android-base:usecase"))
                    } else {
                        usecase(UsecaseModules.Base)
                    }
                    abstraction(findModule())
                    model(findModule())
                }
            }
        }
    }

    private fun createGeneratedClass(project: Project) {

        project.childProjects.values.forEach {
            when (it.name) {
                "abstraction",
                "data",
                "model",
                "presentation",
                "usecase"-> createFile(it)
            }
        }
    }

    private fun createFile(module: Project) {
        val moduleName = module.name.capitalized()
        val file = File(
            "${module.rootProject.rootDir}/build-script/src/main/kotlin",
            "${moduleName}Modules.kt"
        )
        val size = module.childProjects.size
        val firstLine = file.useLines {
            it.firstOrNull()
        }
        if (firstLine != null && firstLine.startsWith("//")) {
            val existingSize = firstLine.substring(2).toInt()
            if (size == existingSize) return
        }
        file.writeText(
            "//$size\n" +
                    "object ${moduleName}Modules {\n" +
                    module.generateChildClass() +
                    "\n}"
        )
    }

    private fun Project.generateChildClass(): String {

        val listClass: List<String> = this.childProjects.map {
            val name = it.value.name.capitalized()
            val path = ":$mainProjectName:${this.name}:${it.key}"
            "\tobject $name : ${this.name.capitalized()}(\"$path\",\"${it.key}\")"
        }
        return listClass.joinToString(separator = "\n") { it }
    }
}