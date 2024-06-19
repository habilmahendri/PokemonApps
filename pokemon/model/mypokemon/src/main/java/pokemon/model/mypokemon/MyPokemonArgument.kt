package pokemon.model.mypokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyPokemonArgument(
    val name:String
):Parcelable