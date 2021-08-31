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
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.model.RoomStartDate
import op.gg.joinus.model.UserInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.util.joinLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private val roomList:MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        val layoutManager = LinearLayoutManager(context)
        binding.rcHomeMatching.layoutManager = layoutManager
        val roomListAdapter = HomeRoomListAdapter(requireContext())

        roomListAdapter.setOnItemClickListner(object:HomeRoomListAdapter.OnItemClickListener{
            override fun onItemClick(item: HomeRoomListItem) {
                val roomJoinFragment = RoomJoinFragment(item)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView_main,roomJoinFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        binding.rcHomeMatching.adapter = roomListAdapter

        // test list
        val room1_user = listOf(UserInfo(17,"",0,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","abcde",567,""),UserInfo(19,"",1,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","cde",848,""))
        val room2_user = listOf(UserInfo(20,"",0,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","xtz",999,""),UserInfo(30,"",1,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","cxxce",8488,""),UserInfo(20,"",0,"https://www.figma.com/file/EwzuN4fMWTdYZ5XVKvcprc/joinus_android?node-id=27%3A1964","xdfdtz",9199,""))
        val room1 = RoomInfo("lol",3,0,567,1,2,5,1234,"너만 오면 고",RoomStartDate(0,31),true,room1_user)
        val room2 = RoomInfo("lol",4,0,999,2,3,5,5678,"빠르게",RoomStartDate(0,30),false,room2_user)
        roomListAdapter.itemList = listOf(HomeRoomListItem(room1),HomeRoomListItem(room2))
        roomListAdapter.notifyDataSetChanged()
        if (roomListAdapter.itemCount != 0){
            binding.layoutNoMatchingRoom.visibility = View.GONE
        }
        fun getRoomList(){
            binding.srlHomeMatching.isRefreshing = true
            val retrofit = RetrofitClient.getInstance()
            val api = retrofit.buildRetrofit()
            val callGetRoomList = api.getRoom()
            callGetRoomList.enqueue(object: Callback<List<RoomInfo>> {
                override fun onResponse(call: Call<List<RoomInfo>>, response: Response<List<RoomInfo>>) {
                    if(!response.isSuccessful){
                        joinLog("response err",response.body().toString())
                        binding.srlHomeMatching.isRefreshing = false
                    }
                    joinLog("response",response.body().toString())
                    val tempList = mutableListOf<HomeRoomListItem>()
                    for (i in response.body()!!){
                        tempList.add(HomeRoomListItem(i))
                    }
                    roomListAdapter.itemList = tempList.toList()
                    roomListAdapter.notifyDataSetChanged()
                    if (roomListAdapter.itemCount != 0){
                        binding.layoutNoMatchingRoom.visibility = View.GONE
                    }
                    else{
                        binding.layoutNoMatchingRoom.visibility = View.VISIBLE
                    }
                    binding.srlHomeMatching.isRefreshing = false
                }
                override fun onFailure(call: Call<List<RoomInfo>>, t: Throwable) {
                    joinLog("response fail",t.toString())
                    binding.srlHomeMatching.isRefreshing = false
                }
            })
        }
        //getRoomList()

        binding.srlHomeMatching.setOnRefreshListener {
            getRoomList()
        }

        binding.fabAddMatching.setOnClickListener{
            val addMatchingFragment = AddMatchingFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView_main,addMatchingFragment)
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

}