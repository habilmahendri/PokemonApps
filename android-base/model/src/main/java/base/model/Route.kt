package base.model

interface Route {
    val alias: String
        get() = this::class.simpleName.orEmpty()
}