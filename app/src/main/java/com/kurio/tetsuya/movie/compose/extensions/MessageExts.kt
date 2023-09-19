package com.kurio.tetsuya.movie.compose.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(showText: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(requireContext(), showText, duration).show()

fun Context.showToast(showText: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, showText, duration).show()