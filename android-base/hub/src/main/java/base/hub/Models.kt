package base.hub

import base.model.Route
import kotlin.reflect.KClass

typealias NavigationData = HashMap<KClass<out Route>, Route>

sealed class ContractDestination {
    data class Activity(val activityClass: KClass<out android.app.Activity>) : ContractDestination()
}
