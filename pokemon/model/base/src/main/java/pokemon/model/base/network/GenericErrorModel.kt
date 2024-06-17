package pokemon.model.base.network

class GenericErrorModel(
    val field: String = "",
    val message: String = "",
    val primaryText: String = "OK",
    val secondaryText: String = "",
    val primaryLink: String = "",
    val secondaryLink: String = "",
    val dismissedLink: String = "",
)
