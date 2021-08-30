package op.gg.joinus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private val roomList:MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

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

        // test list
        val room1_user = listOf(User(17,"",0,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","abcde",567,""),User(19,"",1,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","cde",848,""))
        val room2_user = listOf(User(20,"",0,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","xtz",999,""),User(30,"",1,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","cxxce",8488,""),User(20,"",0,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","xdfdtz",9199,""))
        val room1 = Room("lol",3,0,1,5,1234,"너만 오면 고",Start_date(0,31),true,567,room1_user)
        val room2 = Room("lol",4,0,2,5,4567,"빠르게",Start_date(0,30),false,8488,room2_user)
        roomListAdapter.itemList = listOf(HomeRoomListItem(room1),HomeRoomListItem(room2))
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