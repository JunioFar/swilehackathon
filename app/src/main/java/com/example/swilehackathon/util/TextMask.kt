package com.example.swilehackathon.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object TextMask {

    private const val CPF_MASK = "###.###.###-##"


    fun createCPFMask(editText: EditText): TextWatcher {
        return getTextMask(editText)
    }

    private fun getTextMask(editText: EditText) = object : TextWatcher {

        private var isUpdating = false
        private var enteredStr = ""
        private var old = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            enteredStr = s?.replace("[^0-9]*".toRegex(), "") ?: ""
            var maskedStr = ""

            if (isUpdating) {
                old = enteredStr
                isUpdating = false
                return
            }
            maskedStr += getMaskedStr()

            isUpdating = true
            editText.setText(maskedStr)
            editText.setSelection(maskedStr.length)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {}

        private fun getMaskedStr(): String {
            var s = ""
            var i = 0
            for (m in CPF_MASK.toCharArray()) {
                if (m != '#' && enteredStr.length > old.length ||
                    m != '#' && enteredStr.length < old.length && enteredStr.length != i
                ) {
                    s += m
                    continue
                }
                s += if (enteredStr.isEmpty()) break else enteredStr[i]
                i++
                if (enteredStr.length == i) break
            }
            return s
        }
    }
}