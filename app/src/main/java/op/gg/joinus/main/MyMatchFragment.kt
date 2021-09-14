package op.gg.joinus.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentMyMatchBinding
import op.gg.joinus.login.LoginActivity
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.onboarding.OnboardingActivity
import op.gg.joinus.util.SharedPreferenceManager
import op.gg.joinus.util.joinLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyMatchFragment : Fragment() {
    private lateinit var binding: FragmentMyMatchBinding
    private lateinit var mContext: Context

    private var sub: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_match, container, false)
        sub = SharedPreferenceManager.getString(mContext, "sub")

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun initViewPager() {

        var adapter = PagerAdapter(requireFragmentManager())
        adapter.addFragment(JoinedMatchFragment(), "참여한 매칭")
        adapter.addFragment(MatchByClientFragment(), "내가 만든 매칭")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    /**
     * 참여한 매칭, 내가 만든 매칭 프래그먼트에서 사용
     */



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object {
        private const val TAG = "MyMatchFragment"
    }
}