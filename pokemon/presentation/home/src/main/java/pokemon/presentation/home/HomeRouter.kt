package pokemon.presentation.home

import androidx.fragment.app.FragmentActivity
import base.hub.BaseRouter
import base.hub.NavigationData
import base.hub.RouterHandler
import dagger.hilt.android.scopes.ActivityScoped
import pokemon.hub.DetailContract
import pokemon.model.detail.DetailArgument
import javax.inject.Inject


@ActivityScoped
class HomeRouter @Inject constructor(
    override val activity: FragmentActivity,
    override val map: NavigationData,
) : BaseRouter() {

    fun goToDetail(name:String){
        launch<DetailContract,DetailArgument>(DetailArgument(name))
    }
}