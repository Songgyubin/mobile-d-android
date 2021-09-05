package op.gg.joinus.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.view.menu.MenuBuilder
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentAddMatchingBinding
import op.gg.joinus.model.RoomCreate
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.model.RoomStartDate
import op.gg.joinus.model.UserInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.util.joinLog
import op.gg.joinus.util.setImg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil.setContentView
import op.gg.joinus.databinding.DialogCheckMatchingBinding


class AddMatchingFragment: Fragment() {
    private lateinit var binding:FragmentAddMatchingBinding
    private lateinit var calendar:Calendar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_matching,container,false)

        initView()
        initListener()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        setToolbar()
        setBottomNavigationView()
        super.onStart()
    }

    override fun onStop(){
        returnToolbar()
        returnBottomNavigationView()
        super.onStop()
    }

    private fun returnToolbar(){
        val toolbar = (activity as MainActivity).getBinding().toolbarMain
        toolbar.navigationIcon = null
        toolbar.setNavigationOnClickListener {  }
        toolbar.menu.clear()
        (activity as MainActivity).getBinding().toolbarMainTitle.text = ""
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setToolbar(){
        val toolbar = (activity as MainActivity).getBinding().toolbarMain
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_toolbar_navigation_exit,resources.newTheme())
        toolbar.setNavigationOnClickListener {
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }
        toolbar.inflateMenu(R.menu.menu_add_matching)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.item_add_match_ok->{
                    val game_name: String = "league of legends"
                    // + tier 변경 추가, user_pk값 클라이언트에서 받아오기, game_name when으로 구분하기
                    val highest_tier: Int = 0
                    val user_pk: Int= 1
                    val lowest_tier: Int = 0
                    val people_number: Int = binding.txtNumPeople.text.toString().toInt()
                    val room_name: String = binding.ptTitle.text.toString()
                    val start_date = (
                            calendar.get(Calendar.YEAR).toString() + "-" + (calendar.get(Calendar.MONTH)+1).toString() + "-" +
                                    calendar.get(Calendar.DAY_OF_MONTH).toString() + " " +calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":00")
                    val voice_chat: Boolean = binding.rbVoiceYes.isChecked
                    if (room_name == ""){
                        val builder = Dialog(requireContext())
                        val bindingDialog: DialogCheckMatchingBinding = DataBindingUtil.inflate(
                            LayoutInflater.from(
                                context
                            ), R.layout.dialog_check_matching, null, false
                        )
                        bindingDialog.btnCheckMatching.setOnClickListener {
                            builder.dismiss()
                        }
                        builder.setContentView(bindingDialog.root)
                        builder.show()
                    }
                    else{
                        // + update 필요
                        val roomInfo = RoomCreate(game_name, highest_tier, lowest_tier, people_number, room_name, start_date, user_pk, voice_chat)
                        val retrofit = RetrofitClient.getInstance()
                        val api = retrofit.buildRetrofit()
                        val callPostRoom = api.postRoom(roomInfo)
                        callPostRoom.enqueue(object: Callback<Int> {
                            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                                if(!response.isSuccessful){
                                    joinLog("response err",response.body().toString())
                                }
                                joinLog("response",response.body().toString())
                            }
                            override fun onFailure(call: Call<Int>, t: Throwable) {
                                joinLog("response fail",t.toString())
                            }
                        })
                        (activity as MainActivity).supportFragmentManager.popBackStack()
                    }
                }
                else ->{
                }
            }
            return@setOnMenuItemClickListener true
        }
        (activity as MainActivity).getBinding().toolbarMainTitle.text = "매칭 만들기"
        (activity as MainActivity).getBinding().toolbarMainTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)

    }
    private fun setBottomNavigationView(){
        val bottomNavigationView = (activity as MainActivity).getBinding().bottomNavigationViewMain
        bottomNavigationView.visibility = View.GONE
    }

    private fun returnBottomNavigationView(){
        val bottomNavigationView = (activity as MainActivity).getBinding().bottomNavigationViewMain
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun initView(){
        calendar = Calendar.getInstance()
        // set StartDate
        calendar = Calendar.getInstance()
        val startDate = calendar.get(Calendar.YEAR).toString() + "년 " +
                String.format("%02d",(calendar.get(Calendar.MONTH)+1)) + "월 " +
                String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH)) + "일 " +
                String.format("%02d",calendar.get(Calendar.HOUR)) + "시 " +
                String.format("%02d",calendar.get(Calendar.MINUTE)) + "분"
        binding.txtStartDate.text = startDate
    }

    private fun initListener(){
        // set numPeople button
        binding.btnMinusPeople.setOnClickListener {
            val num = binding.txtNumPeople.text.toString().toInt()
            if (num > 1){
                binding.txtNumPeople.text = (num - 1).toString()
            }
        }
        binding.btnPlusPeople.setOnClickListener {
            val num = binding.txtNumPeople.text.toString().toInt()
            binding.txtNumPeople.text = (num + 1).toString()
        }

        // set start_date button
        binding.btnChangeDate.setOnClickListener {
            var dateString = ""
            var timeString = ""
            val dateListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    dateString = "${year}년 ${String.format("%02d",month+1)}월 ${String.format("%02d",dayOfMonth)}일"
                    // add TimePicker
                    val timeListener =
                        TimePickerDialog.OnTimeSetListener{timepicker,hour,minute ->
                            timeString = "${String.format("%02d",hour)}시 ${String.format("%02d",minute)}분"
                            binding.txtStartDate.text = ("$dateString $timeString")
                            calendar.apply{
                                set(Calendar.YEAR,year)
                                set(Calendar.MONTH,month)
                                set(Calendar.DAY_OF_MONTH,dayOfMonth)
                                set(Calendar.HOUR_OF_DAY,hour)
                                set(Calendar.MINUTE,minute)
                            }
                        }
                    TimePickerDialog(requireContext(),timeListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show()
                }
            val dateBuilder = DatePickerDialog(requireContext(),dateListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
            dateBuilder.show()

        }

        // set voiceChat RadioGroup
        binding.rgVoice.setOnCheckedChangeListener { group, checkedId ->
            val tempBackground = binding.rbVoiceYes.background
            val tempTextColor = binding.rbVoiceYes.textColors
            binding.rbVoiceYes.background = binding.rbVoiceNo.background
            binding.rbVoiceYes.setTextColor(binding.rbVoiceNo.textColors)
            binding.rbVoiceNo.background = tempBackground
            binding.rbVoiceNo.setTextColor(tempTextColor)
        }

        //set tier
        binding.btnChangeTier.setOnClickListener {
            var gameName = ""
            when(binding.rgGame.checkedRadioButtonId){
                binding.rbGameLol.id->{
                    gameName = "league of legends"
                }
            }
            returnToolbar()
            val changeTierFragment = ChangeTierFragment(gameName,this)
            parentFragmentManager.beginTransaction()
                .hide(this)
                .add(R.id.fragmentContainerView_main,changeTierFragment)
                .addToBackStack(null)
                .commit()
        }
    }
    fun getBinding():FragmentAddMatchingBinding{
        return binding
    }
}