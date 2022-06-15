package com.head.views.ui.title

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.head.view.style.GeneralModeTitle
import com.head.views.R
import com.head.views.databinding.FragmentTitleBinding
import com.head.views.databinding.ItemTitleBinding

class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    val TAG = "TitleFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val spinnerViewModel =
            ViewModelProvider(this).get(TitleViewModel::class.java)
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
//        val view =  binding.headTitleBar.getCustomView()
//        val textView = view?.findViewById<TextView>(R.id.textView)
//        textView?.text = "s"

//        binding.headTitleBar.addCustomView(R.layout.item_title){
//
//            val textView = it?.findViewById<TextView>(R.id.textView)
//            textView?.text = "标题"
//        }

        Log.e(TAG, "onCreateView: ", )
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}