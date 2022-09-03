package com.seom.seommain.util.ext

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.setValidationCheckListener(
    validator: (String) -> Int?,
    onResult: (Int?) -> Unit,
    onFinally: () -> Unit
) {
    this.addTextChangedListener { edit ->
        edit?.let {
            /**
             * 아무것도 입력하지 않거나 공백일 때는 에러문 제거
             */
            if (edit.toString().isBlank()) {
                onResult(null)
                return@let
            }
            onResult(validator(edit.toString()))
        }
        onFinally()
    }
}