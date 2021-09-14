package op.gg.joinus.chat

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.model.UserInfo
/*
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


object UserInfoListClassParceler:Parceler<List<UserInfo>>{
    override fun create(parcel: Parcel) = List<UserInfo>(

    )

    override fun List<UserInfo>.write(parcel: Parcel, flags: Int) {

    }


}





class RoomInfoListParcel() : Parcelable {
    var game_name: String? = ""
    var highest_tier: Int = 0
    var is_start: Int = 0
    var leader_pk: Int= 0
    var lowest_tier: Int = 0
    var now_people_cnt:Int = 0
    var people_number: Int = 0
    var pk: Int = 0
    var room_name: String? = ""
    var start_date: String? = ""
    var created_at: String? = ""
    var voice_chat: Boolean = true
    var user_list:List<UserInfo> =listOf()

    constructor(parcel: Parcel) : this() {
        game_name = parcel.readString()
        highest_tier = parcel.readInt()
        is_start = parcel.readInt()
        leader_pk = parcel.readInt()
        lowest_tier = parcel.readInt()
        now_people_cnt = parcel.readInt()
        people_number = parcel.readInt()
        pk = parcel.readInt()
        room_name = parcel.readString()
        start_date = parcel.readString()
        created_at = parcel.readString()
        voice_chat = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(game_name)
        parcel.writeInt(highest_tier)
        parcel.writeInt(is_start)
        parcel.writeInt(leader_pk)
        parcel.writeInt(lowest_tier)
        parcel.writeInt(now_people_cnt)
        parcel.writeInt(people_number)
        parcel.writeInt(pk)
        parcel.writeString(room_name)
        parcel.writeString(start_date)
        parcel.writeString(created_at)
        parcel.writeByte(if (voice_chat) 1 else 0)

        val l = this.user_list
        parcel.writeInt(l.size)
        val l2 = l.iterator()
        while(l2.hasNext()){
            UserInfoClassParcel(l2.next()).writeToParcel(parcel,0)
            (l2.next()).writeToParcel(parcel,0)
        }

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RoomInfoListParcel> {
        override fun createFromParcel(parcel: Parcel): RoomInfoListParcel {
            return RoomInfoListParcel(parcel)
        }

        override fun newArray(size: Int): Array<RoomInfoListParcel?> {
            return arrayOfNulls(size)
        }
    }

}


class UserInfoClassParcel():Parcelable{
    val userInfo:UserInfo? = null
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserInfoClassParcel> {
        override fun createFromParcel(parcel: Parcel): UserInfoClassParcel {
            return UserInfoClassParcel(parcel)
        }

        override fun newArray(size: Int): Array<UserInfoClassParcel?> {
            return arrayOfNulls(size)
        }
    }

}


 */