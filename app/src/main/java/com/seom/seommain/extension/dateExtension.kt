package com.seom.seommain.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
val dateFormat = SimpleDateFormat("yyyy.mm.dd")
fun Date.format(): String = dateFormat.format(this)