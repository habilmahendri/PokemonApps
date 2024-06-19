package pokemon.hub

import android.app.Activity
import base.model.NoResult
import pokemon.hub.base.PokemonActivityContract
import pokemon.model.mypokemon.MyPokemonArgument
import kotlin.reflect.KClass

class MyPokemonContract(override val activityClass: KClass<out Activity>) :
    PokemonActivityContract<MyPokemonArgument, NoResult>(MyPokemonArgument::class, NoResult::class)