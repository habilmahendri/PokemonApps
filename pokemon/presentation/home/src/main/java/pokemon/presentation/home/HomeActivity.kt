package pokemon.presentation.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import pokemon.model.home.PokemonItems
import pokemon.presentation.base.toast
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val homeStateMachine:HomeStateMachine by viewModels()

    @Inject
    lateinit var homeRouter:HomeRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = Unit, block = {
                homeStateMachine.sendEvent(HomeEvent.GetPokemons)
                homeStateMachine.effect.collect{
                    when(it){
                        is HomeEffect.GoToDetailPokemon -> {
                            toast(it.pokemon.name)
                            homeRouter.goToDetail(it.pokemon.name)
                        }
                    }
                }
            } )
            val state by homeStateMachine.state.collectAsState()
            MaterialTheme {
                when(state){
                    HomeState.Failed -> {
                        toast("gagal")
                    }
                    HomeState.Loading -> toast("loading")
                    is HomeState.Loaded -> {
                        LoadedScreen(state = state as HomeState.Loaded){
                            homeStateMachine.sendEvent(it)
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun LoadedScreen(state: HomeState.Loaded,event: (HomeEvent) -> Unit,) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(items = state.pokemons) {
                PokemonItems(it){
                    event(HomeEvent.OnPokemonClicked(it))
                }
            }
        }
    }

    @Composable
    fun PokemonItems(data:PokemonItems, onItemCLicked: (PokemonItems) -> Unit,) {
        Column(modifier = Modifier.padding(10.dp).clickable {
            onItemCLicked(data)
        }) {
            ConstraintLayout(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(8.dp),
                    )
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                val (name, image) = createRefs()
                AsyncImage(
                    model = data.url,
                    contentDescription = "image pokemon",
                    modifier = Modifier.constrainAs(image){
                        start.linkTo(parent.start, 12.dp)
                        end.linkTo(parent.end, 12.dp)
                        top.linkTo(parent.top, 12.dp)
                    }
                )
                Text(
                    text = data.name,
                    modifier = Modifier.constrainAs(name) {
                        top.linkTo(image.bottom)
                        start.linkTo(image.start)
                        bottom.linkTo(parent.bottom,12.dp)
                        end.linkTo(image.end)
                    },
                )

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        PokemonItems(data = PokemonItems(name = "Pokemon", url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png")){

        }
    }
}