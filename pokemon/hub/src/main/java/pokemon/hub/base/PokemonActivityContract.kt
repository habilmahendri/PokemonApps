package pokemon.hub.base

import android.app.Activity
import android.os.Parcelable
import base.hub.ContractDestination
import base.hub.BaseContract
import kotlin.reflect.KClass

abstract class PokemonActivityContract<Args : Parcelable, Result : Parcelable>(
    argsClass: KClass<Args>,
    resultClass: KClass<Result>,
) : BaseContract<Args, Result>(argsClass, resultClass) {

    abstract val activityClass: KClass<out Activity>

    override val contractDestination: ContractDestination
        get() = ContractDestination.Activity(activityClass)
}
