package pokemon.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.presentation.BaseActivity
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import pokemon.model.detail.DetailArgument
import pokemon.model.detail.Pokemon
import pokemon.presentation.base.PokemonActivity
import pokemon.presentation.base.toast

@AndroidEntryPoint
class DetailActivity : PokemonActivity() {
    private val detailStateMachine:DetailStateMachine by viewModels()
    private val data by parcelableArgs<DetailArgument>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val payload = data ?: return
        Log.d("payloadintent", payload.name)
        setContent {
            LaunchedEffect(key1 = Unit, block = {
                detailStateMachine.sendEvent(DetailEvent.GetPokemon(payload.name))
                detailStateMachine.effect.collect{
                    when(it){
                        is DetailEffect.ShowToast -> {
                            toast(it.probability.toString())
                        }
                    }
                }
            } )
            val state by detailStateMachine.state.collectAsState()

            MaterialTheme {
                when(state){
                    DetailState.Failed -> {
                        toast("gagal")
                    }
                    DetailState.Loading -> toast("loading")
                    is DetailState.Loaded -> {
                        LoadedScreen(data = state as DetailState.Loaded)
                    }

                }
            }
        }
    }

    @Composable
    fun LoadedScreen(data:DetailState.Loaded) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = data.pokemon.image,
                contentDescription = "image pokemon",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
            Text(
                text = data.pokemon.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            LazyRow(content = {
                items(data.pokemon.types) {
                    Chip(label = it, modifier = Modifier.padding(5.dp))
                }
            })
            Text(
                text = "Moves:  ${data.pokemon.moves}",
                modifier = Modifier.padding(vertical = 15.dp)
            )
            Button(
                onClick = {
                    detailStateMachine.sendEvent(DetailEvent.CatchPokemon)
                },
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Text("Tangkap Pokemon")
            }

            if(data.isButtonRelease){
                Button(
                    onClick = {
                        detailStateMachine.sendEvent(DetailEvent.CatchPokemon)
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Text("Release")
                }
            }
        }
    }

    @Composable
    fun Chip(
        label: String,
        modifier: Modifier = Modifier
    ) {
        Surface(
            shape = CircleShape,
            color = Color.LightGray,
            modifier = modifier
        ) {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}