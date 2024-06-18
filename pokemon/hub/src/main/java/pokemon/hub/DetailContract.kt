package pokemon.hub

import android.app.Activity
import base.model.NoResult
import pokemon.hub.base.PokemonActivityContract
import pokemon.model.detail.DetailArgument
import kotlin.reflect.KClass

class DetailContract(override val activityClass: KClass<out Activity>) :
    PokemonActivityContract<DetailArgument, NoResult>(DetailArgument::class, NoResult::class)