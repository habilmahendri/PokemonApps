package pokemon.data.core.network

import pokemon.model.core.ServerConfig

object DefaultServerConfig {
    val devEnv = ServerConfig(
        "https://api.skadik.dev.learningsuite.id/",
        "",
        "",
        true
    )

    val prodEnv = ServerConfig(
        "https://api.skadik.dev.learningsuite.id/",
        "",
        "",
        false
    )
}