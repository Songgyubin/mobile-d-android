package op.gg.joinus.chat
/*
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.model.UserInfo

@Parcelize
class RoomInfoClassParcel(val userList:List<UserInfo>):Parcelable{
    companion object : Parceler<RoomInfoClassParcel> {
        override fun RoomInfoClassParcel.write(dest: Parcel, flags: Int) {

        }

        override fun create(parcel: Parcel): RoomInfoClassParcel = TODO()
    }
}


object RoomInfoClassParceler : Parceler<RoomInfo> {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun create(parcel: Parcel) = RoomInfo(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readBoolean(),
        UserInfoListClassParceler.create(parcel)
    )

    override fun RoomInfo.write(parcel: Parcel, flags: Int) {
        parcel.writeInt()
    }
}

private fun Parcel.writeInt() {
    TODO("Not yet implemented")
}

object UserInfoListClassParceler:Parceler<List<UserInfo>>{
    override fun create(parcel: Parcel) = List<UserInfo>(

    )

    override fun List<UserInfo>.write(parcel: Parcel, flags: Int) {

    }


}


object UserInfoClassParceler:Parceler<UserInfo>{
    override fun create(parcel: Parcel) = UserInfo(

    )

    override fun UserInfo.write(parcel: Parcel, flags: Int) {

    }

}

 */