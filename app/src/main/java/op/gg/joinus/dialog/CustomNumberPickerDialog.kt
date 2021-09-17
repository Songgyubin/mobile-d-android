package op.gg.joinus.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.NumberPicker
import op.gg.joinus.R

class CustomNumberPickerDialog(
    context: Context,
    private val res: Int,
    private val ageInterface: AgeInterface
) : Dialog(context) {
    private lateinit var numberPicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(res)

        initNumberPicker()
        initButton()
    }

    private fun initButton() {
        findViewById<Button>(R.id.btn_age_cancel).setOnClickListener { this.dismiss() }
        findViewById<Button>(R.id.btn_age_confirm).setOnClickListener {
            setAge()
            this.dismiss()
        }
    }

    private fun initNumberPicker() {
        numberPicker = findViewById<NumberPicker>(R.id.np_age)
        numberPicker.minValue = 20
        numberPicker.maxValue = 40
        numberPicker.value = 25
    }

    private fun setAge() {
        ageInterface.setAge(numberPicker.value)
    }

}