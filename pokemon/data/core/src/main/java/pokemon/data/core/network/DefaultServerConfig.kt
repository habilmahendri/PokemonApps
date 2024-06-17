package pokemon.data.core.network

import pokemon.model.core.ServerConfig

object DefaultServerConfig {
    val devEnv = ServerConfig(
        "https://pokeapi.co/api/",
        true
    )

    val prodEnv = ServerConfig(
        "https://pokeapi.co/api/",
        false
    )
}