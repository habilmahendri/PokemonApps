package base.hub

interface RouterHandler {
    fun onNavigated(contract: BaseContract<*,*>)
    fun onRouteNotFound(className : String)
}