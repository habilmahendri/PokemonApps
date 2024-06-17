package base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ImageType : Parcelable {
    @Parcelize
    data class Url(val url: String,val name:String = "") : ImageType()

    @Parcelize
    data class Res(val res: Int) : ImageType()

    @Parcelize
    data class Bitmap(val bitmap: android.graphics.Bitmap) : ImageType()

    companion object {
        val Init = Res(0)
    }
}