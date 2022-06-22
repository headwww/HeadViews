package com.head.views.ui.button

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.head.view.HeadButton
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
                    binding.headButton.setHeadButtonNormalGradientFrom(it)
                    i=0
                }else{
                    binding.headButton.setHeadButtonNormalGradientTo(it)
                    i++
                }
            }else{
                binding.headButton.setHeadButtonNormalBackgroundColor(it)
            }
        }
        binding.colorPickerView2.addOnColorChangedListener {
            if (buttonViewModel.check1.value == true){
                if (i==1){
                    binding.headButton.setHeadButtonPressedGradientFrom(it)
                    i=0
                }else{
                    binding.headButton.setHeadButtonPressedGradientTo(it)
                    i++
                }
            }else{
                binding.headButton.setHeadButtonPressedBackgroundColor(it)
            }
        }
        binding.checkBox11.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.headButton.setHeadButtonNormalStrokeColor(Color.RED)
                binding.headButton.setHeadButtonPressedStrokeColor(Color.YELLOW)
                binding.headButton.setHeadButtonNormalStrokeWidth(6F)
                binding.headButton.setHeadButtonPressedStrokeWidth(6F)
                binding.headButton.setHeadButtonPressedStrokeDashWidth(12F)
                binding.headButton.setHeadButtonPressedStrokeDashGap(12F)
            }else{
                binding.headButton.setHeadButtonNormalStrokeWidth(0F)
                binding.headButton.setHeadButtonPressedStrokeWidth(0F)
                binding.headButton.setHeadButtonPressedStrokeDashWidth(0F)
                binding.headButton.setHeadButtonPressedStrokeDashGap(0F)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}