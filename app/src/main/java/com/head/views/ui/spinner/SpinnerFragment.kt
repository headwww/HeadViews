package com.head.views.ui.spinner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.head.views.R
import com.head.views.databinding.FragmentSpinnerBinding

class SpinnerFragment : Fragment() {

    private var _binding: FragmentSpinnerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val spinnerViewModel =
            ViewModelProvider(this).get(SpinnerViewModel::class.java)
        _binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        binding.headSpinner.setData(List<String>(4) { "Item$it" })
        binding.headSpinner2.setData(List<String>(4) { "Item$it" })
        binding.headSpinner3.setData(List<String>(4) { "Item$it" })
        binding.headSpinner4.setData(List<String>(4) { "Item$it" })
        binding.headSpinner4.setHeadSpinnerItemIcon(
            arrayListOf(
                R.drawable.ic_baseline_search_24,
                R.drawable.ic_baseline_mic_24,
                R.drawable.ic_baseline_cancel_24,
                null,
                R.drawable.ic_baseline_cancel_24,
                R.drawable.ic_baseline_cancel_24,
                R.drawable.ic_baseline_cancel_24,
            )
        )
        binding.headSpinner5.setData(arrayListOf("Dismiss监听","Item监听"))
        binding.headSpinner5.setOnDismissListener {
            Toast.makeText(requireActivity(), "OnDismiss", Toast.LENGTH_SHORT).show()
        }
        binding.headSpinner5.setOnItemClickListener{
            item,position->
            Toast.makeText(requireActivity(), "$item 点击了:$position", Toast.LENGTH_SHORT).show()
        }
        binding.headSpinner6.setData(List<Person>(4) { Person("Item$it",it) })
        binding.headSpinner6.setItemFormat {
            it as Person
            "姓名：${it.name},年龄${it.age}"
        }
        binding.headSpinner6.setSelection(3)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}