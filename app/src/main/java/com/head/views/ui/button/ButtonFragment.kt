package com.head.views.ui.button

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.head.views.databinding.FragmentButtonBinding

class ButtonFragment : Fragment() {

    private var _binding: FragmentButtonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val buttonViewModel =
            ViewModelProvider(this).get(ButtonViewModel::class.java)

        _binding = FragmentButtonBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewModel = buttonViewModel
        binding.lifecycleOwner = this

        var i = 0
        binding.colorPickerView1.addOnColorChangedListener {
            if (buttonViewModel.check1.value == true){
                if (i==1){
                    binding.headButton.setHeadNormalGradientFrom(it)
                    i=0
                }else{
                    binding.headButton.setHeadNormalGradientTo(it)
                    i++
                }
            }else{
                binding.headButton.setHeadNormalBackgroundColor(it)
            }
        }
        binding.colorPickerView2.addOnColorChangedListener {
            if (buttonViewModel.check1.value == true){
                if (i==1){
                    binding.headButton.setHeadPressedGradientFrom(it)
                    i=0
                }else{
                    binding.headButton.setHeadPressedGradientTo(it)
                    i++
                }
            }else{
                binding.headButton.setHeadPressedBackgroundColor(it)
            }
        }
        binding.checkBox11.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.headButton.setHeadNormalStrokeColor(Color.RED)
                binding.headButton.setHeadPressedStrokeColor(Color.YELLOW)
                binding.headButton.setHeadNormalStrokeWidth(6)
                binding.headButton.setHeadPressedStrokeWidth(6)
                binding.headButton.setHeadPressedStrokeDashWidth(12F)
                binding.headButton.setHeadPressedStrokeDashGap(12F)
            }else{
                binding.headButton.setHeadNormalStrokeWidth(0)
                binding.headButton.setHeadPressedStrokeWidth(0)
                binding.headButton.setHeadPressedStrokeDashWidth(0F)
                binding.headButton.setHeadPressedStrokeDashGap(0F)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}