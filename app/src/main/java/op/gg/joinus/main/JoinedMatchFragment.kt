package op.gg.joinus.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentMymatchJoinedMatchingBinding
import op.gg.joinus.databinding.FragmentOnboarding1Binding
import op.gg.joinus.onboarding.OnboardingActivity
import op.gg.joinus.util.joinLog

class JoinedMatchFragment : Fragment() {

    private lateinit var binding: FragmentMymatchJoinedMatchingBinding
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_mymatch_joined_matching,
                container,
                false
            )
        joinLog(TAG,"create")
        return binding.root
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
        private const val TAG = "JoinedMatchFragment"
    }
}