package base.hub

import base.model.Route
import kotlin.reflect.KClass

typealias NavigationData = HashMap<KClass<out Route>, Route>