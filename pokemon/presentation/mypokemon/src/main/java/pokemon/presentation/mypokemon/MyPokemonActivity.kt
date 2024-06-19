package pokemon.presentation.mypokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import pokemon.model.mypokemon.PokemonItems
import pokemon.presentation.base.PokemonActivity
import pokemon.presentation.base.toast

@AndroidEntryPoint
class MyPokemonActivity : PokemonActivity() {
    private val myPokemonStateMachine: MyPokemonStateMachine by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = Unit, block = {
                myPokemonStateMachine.sendEvent(MyPokemonEvent.GetMyPokemon)
//                myPokemonStateMachine.effect.collect {
//                    when (it) {
//                        is MyPokemonEvent.GoToDetailPokemon -> {
//                            toast(it.pokemon.name)
//                            homeRouter.goToDetail(it.pokemon.name)
//                        }
//
//                        HomeEffect.GotoMyPokemon -> {
//                            homeRouter.goToMyPokemon()
//                        }
//                    }
//                }
            })
            val state by myPokemonStateMachine.state.collectAsState()

            MaterialTheme {
                when (state) {
                    MyPokemonState.Failed -> {
                        toast("gagal")
                    }

                    MyPokemonState.Loading -> toast("loading")
                    is MyPokemonState.Loaded -> {
                        LoadedScreen(state = state as MyPokemonState.Loaded) {
                            myPokemonStateMachine.sendEvent(it)
                        }
                    }
                }
            }
        }
    }
    @Composable
    fun LoadedScreen(state: MyPokemonState.Loaded, event: (MyPokemonEvent) -> Unit) {
        ConstraintLayout {
            val (grid, fab) = createRefs()
            Text(
                modifier = Modifier.fillMaxWidth().padding(15.dp),
                text = "My Pokemon")

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(grid) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = state.pokemons) {
                    PokemonItems(it) {
                        event(MyPokemonEvent.OnPokemonClicked(it))
                    }
                }
            }
        }

    }

    @Composable
    fun PokemonItems(data: PokemonItems, onItemCLicked: (PokemonItems) -> Unit) {
        Column(modifier = Modifier
            .padding(10.dp)
            .clickable {
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
                    modifier = Modifier.constrainAs(image) {
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
                        bottom.linkTo(parent.bottom, 12.dp)
                        end.linkTo(image.end)
                    },
                )

            }
        }
    }
}