package com.head.views.ui.title

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.head.view.HeadEditTextView
import com.head.views.BilibiliActivity
import com.head.views.R
import com.head.views.WechatActivity
import com.head.views.databinding.FragmentTitleBinding

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

//        binding.headButton2.setOnClickListener {
//        }
        binding.headTitleBar.getCustomView()?.let {
            it.findViewById<HeadEditTextView>(R.id.headEditTextView).setOnTextareaClickListener {
                startActivity(Intent(requireActivity(), BilibiliActivity::class.java))
            }

        }
        binding.headTitle02.getCustomView()?.let {
            it.findViewById<ImageView>(R.id.searchWx).setOnClickListener {
                startActivity(Intent(requireActivity(), WechatActivity::class.java))

            }

        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}