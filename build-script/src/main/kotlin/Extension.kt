
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.`abstraction`(abstraction: Abstraction?, allowExpose : Boolean = false){
    if (abstraction == null) return
    add(if (allowExpose) "api" else "implementation", project(abstraction.path))
}

fun DependencyHandler.`abstraction`(vararg abstraction: Abstraction?){
    abstraction.forEach {
        abstraction(it)
    }
}

fun DependencyHandler.`model`(data: Model?){
    if (data == null) return
    add("api", project(data.path))
}

fun DependencyHandler.`model`(vararg data: Model?){
    data.forEach {
        model(it)
    }
}

fun DependencyHandler.`usecase`(data: Usecase?){
    if (data == null) return
    add("api", project(data.path))
}

fun DependencyHandler.`usecase`(vararg data: Usecase?){
    data.forEach {
        usecase(it)
    }
}

fun DependencyHandler.`presentation`(data: Presentation?){
    if (data == null) return
    add("api", project(data.path))
}

fun DependencyHandler.`presentation`(vararg data: Presentation?){
    data.forEach {
        presentation(it)
    }
}

fun DependencyHandler.`data`(data: Data?){
    if (data == null) return
    add("api", project(data.path))
}

fun DependencyHandler.`data`(vararg data: Data?){
    data.forEach {
        data(it)
    }
}

fun DependencyHandler.`api`(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)


inline fun<reified T : Modules> Project.findModule(featureName : String = name): T?{
    val modules = when(T::class){
        Abstraction::class -> AbstractionModules::class.nestedClasses
        Usecase::class -> UsecaseModules::class.nestedClasses
        Data::class -> DataModules::class.nestedClasses
        Model::class -> ModelModules::class.nestedClasses
        Presentation::class -> PresentationModules::class.nestedClasses
        else  -> return null
    }.mapNotNull { it.objectInstance as? T }
    return modules.firstOrNull { it.featureName == featureName }

}
//fun com.android.build.gradle.LibraryExtension.`kotlinOptions`(configure: Action<org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions>): Unit =
//    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlinOptions", configure)
//
//



private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency =

    uncheckedCast(
        project(
            if (configuration != null) mapOf("path" to path, "configuration" to configuration)
            else mapOf("path" to path)
        )
    )

@Suppress("unchecked_cast", "nothing_to_inline")
internal inline fun <T> uncheckedCast(obj: Any?): T =
    obj as T

fun Project.`androidConfiguration`(configure: Action<LibraryExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("android", configure)
