package com.head.views.ui.edittext

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.head.views.TAG
import com.head.views.databinding.FragmentEditTextBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentEditTextBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val editTextViewViewModel =
            ViewModelProvider(this).get(EditTextViewViewModel::class.java)
        _binding = FragmentEditTextBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewModel = editTextViewViewModel
        binding.lifecycleOwner = this
        binding.checkbox1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) binding.headEditTextView.setHeadType(-2) else binding.headEditTextView.setHeadType(-1)
         }
        var i = 0
        binding.colorPickerView.addOnColorChangedListener {
            if (editTextViewViewModel.check1.value == true){
                if (i==1){
                    binding.headEditTextView.setHeadGradientFrom(it)
                    i=0
                }else{
                    binding.headEditTextView.setHeadGradientTo(it)
                    i++
                }
            }else{
                binding.headEditTextView.setHeadBackgroundColor(it)
            }
        }
        binding.headEditTextView.setOnLeftDrawableClickListener {
            Log.e(TAG, "onCreateView: 左边", )
        }
        binding.headEditTextView.setOnRightDrawableClickListener {
            Log.e(TAG, "onCreateView: 右边", )
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

