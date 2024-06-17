package pokemon.data.core.datasource

import base.model.BuildFlavor
import pokemon.data.core.network.DefaultServerConfig
import pokemon.model.core.ServerConfig

class ServerDataSource {
    fun getServerConfig(flavor: BuildFlavor): ServerConfig {
        return when (flavor) {
            BuildFlavor.DEV -> DefaultServerConfig.devEnv
            BuildFlavor.STAGING -> DefaultServerConfig.devEnv
            BuildFlavor.PROD -> DefaultServerConfig.prodEnv
        }
    }
}