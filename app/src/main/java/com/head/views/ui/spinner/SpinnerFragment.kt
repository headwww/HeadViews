package com.head.views.ui.spinner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.head.views.databinding.FragmentSpinnerBinding

class SpinnerFragment : Fragment() {

    private var _binding: FragmentSpinnerBinding? = null
    private val binding get() = _binding!!
    private var arrayList = arrayListOf<DataTest>(DataTest("舒文",25),DataTest("闵天赐",26))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val spinnerViewModel =
            ViewModelProvider(this).get(SpinnerViewModel::class.java)

        _binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.headSpinner.setOnClickListener {
            Log.e("TAG", "onCreateView: ")
        }

        binding.headSpinner.setItemFormat{
            it as DataTest
            "姓名：${it.name} ; 年龄： ${it.age}"
        }
        binding.headSpinner.setData(arrayList)
//        binding.headSpinner.setData(arrayListOf("12","213"))
        binding.headSpinner.setOnItemClickListener { item, position ->
            Log.e("TAG", "onCreateView:$item ")
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}