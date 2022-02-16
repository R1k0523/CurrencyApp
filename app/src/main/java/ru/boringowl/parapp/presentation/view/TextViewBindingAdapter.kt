package ru.boringowl.parapp.presentation.view

import androidx.databinding.BindingAdapter
import android.widget.TextView
import androidx.databinding.InverseBindingAdapter
import java.lang.Exception
import java.math.BigDecimal
import java.math.MathContext

class TextViewBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("android:text")
        fun setText(view: TextView, value: BigDecimal?) {
            if (value == null) return
            view.text = value.toPlainString()
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
        fun getTextString(view: TextView): BigDecimal {
            try {
                return BigDecimal(view.text.toString()).round(MathContext(15))
            } catch (e: Exception) {
                return BigDecimal.ZERO
            }
        }
    }
}