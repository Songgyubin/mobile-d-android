package op.gg.joinus.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import op.gg.joinus.R
import op.gg.joinus.databinding.ActivityMainBinding
import op.gg.joinus.databinding.HomeFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var binding:HomeFragmentBinding
    private val roomList:MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false)

        val layoutManager = LinearLayoutManager(context)
        binding.rcHomeMatching.layoutManager = layoutManager
        val roomListAdapter = HomeRoomListAdapter(requireContext())

        roomListAdapter.setOnItemClickListner(object:HomeRoomListAdapter.OnItemClickListener{
            override fun onItemClick(item: HomeRoomListItem) {
                val roomJoinFragment = RoomJoinFragment(item)
                val currentActivity:MainActivity
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView_main,roomJoinFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        binding.rcHomeMatching.adapter = roomListAdapter

        roomListAdapter.itemList = listOf(HomeRoomListItem("5인큐 빠르게 ㄱ","schan97 | 17:05","방금전","3/5",resources.getString(R.string.imgUrl_lol)),
            HomeRoomListItem("5인큐 아무나 오셈","schan9788 | 17:04","방금전","2/5",resources.getString(R.string.imgUrl_lol))
            )
        roomListAdapter.notifyDataSetChanged()


        if (roomListAdapter.itemCount != 0){
            binding.layoutNoMatchingRoom.visibility = View.GONE
        }

        /*
        // 함수 정의 할것
        val baseUrl = "http://ec2-3-128-67-103.us-east-2.compute.amazonaws.com"
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(joinus::class.java)

        val callGetRoom = api.getRoom()
        callGetRoom.enqueue(object:Callback<List<Room>>{
            override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                if(!response.isSuccessful){
                    Log.d("response err",response.body().toString())
                    return
                }
                Log.d("response",response.body().toString())
            }
            override fun onFailure(call: Call<List<Room>>, t: Throwable) {
                Log.d("response fail",t.toString())
                return
            }
        })
         */

        binding.srlHomeMatching.setOnRefreshListener {
            //정의 된 함수 추가
            binding.srlHomeMatching.isRefreshing = false
        }


        return binding.root
    }

}