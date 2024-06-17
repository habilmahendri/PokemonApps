package pokemon.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import pokemon.presentation.base.toast

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val homeStateMachine:HomeStateMachine by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = Unit, block = {
                homeStateMachine.sendEvent(HomeEvent.GetPokemons)
            } )
            val state by homeStateMachine.state.collectAsState()
            MaterialTheme {
                when(state){
                    HomeState.Failed -> {
                        toast("gagal")
                    }
                    HomeState.Loading -> toast("loading")
                    is HomeState.Loaded -> toast((state as HomeState.Loaded).pokemons.size.toString())
                }
                Text(text = "abc")
            }
        }
    }
}