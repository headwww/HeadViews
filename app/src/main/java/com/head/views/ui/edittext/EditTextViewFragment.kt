package com.head.views.ui.edittext

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.head.view.HeadEditTextView
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
            if (isChecked) binding.headEditTextView.setHeadEditTextType(HeadEditTextView.EditTextType.TEXTVIEW) else binding.headEditTextView.setHeadEditTextType(HeadEditTextView.EditTextType.EDITTEXT)
         }
        var i = 0
        binding.colorPickerView.addOnColorChangedListener {
            if (editTextViewViewModel.check1.value == true){
                if (i==1){
                    binding.headEditTextView.setHeadEditTextGradientFrom(it)
                    i=0
                }else{
                    binding.headEditTextView.setHeadEditTextGradientTo(it)
                    i++
                }
            }else{
                binding.headEditTextView.setHeadEditTextBackgroundColor(it)
            }
        }
        binding.headEditTextView.setOnLeftDrawableClickListener {
            Log.d(TAG, "onCreateView: Left" )
        }
        binding.headEditTextView.setOnRightDrawableClickListener {
            Log.d(TAG, "onCreateView: Right" )
        }
        binding.headEditTextView.setOnTopDrawableClickListener {
            Log.d(TAG, "onCreateView: Top", )
        }
        binding.headEditTextView.setOnBottomDrawableClickListener {
            Log.i(TAG, "onCreateView: Bottom", )
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

