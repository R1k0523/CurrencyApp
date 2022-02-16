package ru.boringowl.parapp.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import ru.boringowl.parapp.databinding.ActivityMainBinding
import ru.boringowl.parapp.presentation.viewmodel.MainViewModel
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var viewModel: MainViewModel = MainViewModel()
    var reversed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewmodel = viewModel
        binding.loading = false
        setContentView(binding.root)
        binding.btnSwap.setOnClickListener {
            val toPosition = binding.toSpinner.selectedItemPosition
            val fromPosition = binding.fromSpinner.selectedItemPosition
            binding.toSpinner.setSelection(fromPosition)
            binding.fromSpinner.setSelection(toPosition)
            if (reversed)
                binding.amountTo.text = binding.amountFrom.text
            else
                binding.amountFrom.text = binding.amountTo.text
        }
        binding.fromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                convert()
            }
        }
        binding.toSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                convert()
            }
        }
        binding.amountFrom.setOnFocusChangeListener { view, b -> if (b) reversed = false }
        binding.amountTo.setOnFocusChangeListener { view, b -> if (b) reversed = true }
        binding.amountFrom.doAfterTextChanged { if (!reversed) convert(false) }
        binding.amountTo.doAfterTextChanged { if (reversed) convert(false) }
    }

    fun convert(loading: Boolean = true) {
        binding.loading = loading
        val from = binding.fromSpinner.selectedItem as String
        val to = binding.toSpinner.selectedItem as String
        val rate = viewModel.getRates(from, to) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
        rate.observe(this) {
            if (it != null)
                if (reversed) {
                    val text = (viewModel.to.value!!.divide(it.rate.toBigDecimal(), 2, RoundingMode.HALF_UP)).toPlainString()
                    binding.amountFrom.setText(text)
                } else {
                    val text = (viewModel.from.value!!.multiply(it.rate.toBigDecimal())).toPlainString()
                    binding.amountTo.setText(text)
                }
            binding.loading = false
        }
    }
}