package op.gg.joinus.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window

abstract class CustomDialog(context: Context, private val res: Int) : Dialog(context) {
    var dialog: Dialog = Dialog(context)

    fun setDialog() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(res)
    }

    fun showDialog() {
        dialog.show()
    }

}