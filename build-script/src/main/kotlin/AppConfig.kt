import java.text.SimpleDateFormat
import java.util.*

object AppConfig {
    const val compileSdk = 34
    const val applicationId = "com.pokemonaps"
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val kotlin = "1.4.20"
    const val composeCompilerVersion = "1.4.6"

    fun generateVersionBuild() : String{
        val time = Date(System.currentTimeMillis())
        val format = SimpleDateFormat("ddMMHH", Locale.ENGLISH)
        val extraVer = format.format(time)
        return "$versionName.$extraVer"
    }
}