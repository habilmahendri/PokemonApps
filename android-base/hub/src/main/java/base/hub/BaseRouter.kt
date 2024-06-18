package base.hub

import android.os.Parcelable
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import base.model.NoArgs
import base.model.Route

abstract class BaseRouter : DefaultLifecycleObserver {
    protected abstract val activity: FragmentActivity
    protected abstract val map: NavigationData


    private val registry by lazy { activity.activityResultRegistry }

    private val mutableSet = mutableSetOf<ActivityResultLauncher<Parcelable>>()

    protected inline fun <reified T> launch(onSuccess: () -> Unit = {}) where T : Route, T : BaseContract<NoArgs, *> {
        val kclass = T::class
        try {
            val contract = map[kclass] as? BaseContract<*, *> ?: throw Exception()
            val intent = contract.createIntent(activity, null)
            activity.startActivity(intent)
            onSuccess()
        } catch (e: Exception) {
        }
    }

    protected inline fun <reified T, reified Args> launch(args: Args, onSuccess: () -> Unit = {}) where T : Route, T : BaseContract<Args, *>, Args : Parcelable {
        val kclass = T::class
        val contract = map[kclass] as? T ?: throw Exception()
        try {
            val intent = (map[kclass] as? T)?.createIntent(activity, args)
            activity.startActivity(intent)
            onSuccess()
        } catch (e: Exception) {
        }
    }
}