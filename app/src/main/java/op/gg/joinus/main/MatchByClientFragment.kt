package op.gg.joinus.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentMymatchMatchByClientBinding
import op.gg.joinus.databinding.FragmentOnboarding1Binding
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.onboarding.OnboardingActivity
import op.gg.joinus.util.SharedPreferenceManager
import op.gg.joinus.util.joinLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchByClientFragment : Fragment() {

    private lateinit var binding: FragmentMymatchMatchByClientBinding
    private lateinit var mContext: Context
    private lateinit var roomListAdapter: HomeRoomListAdapter
    private var sub: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_mymatch_match_by_client,
                container,
                false
            )

        sub = SharedPreferenceManager.getString(mContext, "sub")

        initView()
        initListener()

        return binding.root
    }

    private fun initView() {
        binding.recyclerMatcheByClient.apply {
            this.layoutManager = LinearLayoutManager(mContext)
        }
        roomListAdapter = HomeRoomListAdapter(requireContext())
        binding.recyclerMatcheByClient.adapter = roomListAdapter

        //set layoutNoMatchingRoom
        if (roomListAdapter.itemCount != 0) {
            binding.layoutNoMatchingRoom.visibility = View.GONE
        }

        sub?.let { getMatchByClientRoom(it) }
    }

    private fun initListener() {
        //set Adapter click listener
        roomListAdapter.setOnItemClickListner(object : HomeRoomListAdapter.OnItemClickListener {
            override fun onItemClick(item: HomeRoomListItem) {
                val roomJoinFragment = RoomJoinFragment(item)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView_main, roomJoinFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        //set swipeRefreshLayout listener
        binding.srlMatchByClientMatching.setOnRefreshListener {
            sub?.let {
                getMatchByClientRoom(it)
            }
        }
    }

    /**
     * 내가 만든 방
     * myroom = 1
     */
    private fun getMatchByClientRoom(sub: String) {
        val result: Call<List<RoomInfo>> =
            RetrofitClient.getInstance().buildRetrofit().getMatchedRoom(sub, 1)
        result.enqueue(object : Callback<List<RoomInfo>> {
            override fun onResponse(
                call: Call<List<RoomInfo>>,
                response: Response<List<RoomInfo>>
            ) {
                if (!response.isSuccessful) {
                    joinLog("response err", response.body().toString())
                    binding.srlMatchByClientMatching.isRefreshing = false
                }

                if (response.body().isNullOrEmpty()) {
                    joinLog(TAG, "matchedClient is null")
                    binding.layoutNoMatchingRoom.visibility = View.VISIBLE
                } else {
                    binding.layoutNoMatchingRoom.visibility = View.GONE
                    response.body()!!.map { HomeRoomListItem(it) }.let { homeRoomListItem ->
                        roomListAdapter.let { adapter ->
                            adapter.itemList = homeRoomListItem
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
                binding.srlMatchByClientMatching.isRefreshing = false
            }

            override fun onFailure(call: Call<List<RoomInfo>>, t: Throwable) {
                joinLog(TAG, "error: ${t.message}")
                binding.srlMatchByClientMatching.isRefreshing = false
            }

        })
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()

        joinLog(TAG, "onResume")
    }


    companion object {
        private const val TAG = "MatchByClientFragment"
    }
}