package com.kurio.tetsuya.movie.compose.extensions

import com.kurio.tetsuya.movie.compose.core.DateConstants.DD_MMMM_HH_MM_24
import com.kurio.tetsuya.movie.compose.core.DateConstants.DD_MMMM_HH_MM_A_12
import com.kurio.tetsuya.movie.compose.core.DateConstants.DD_MMMM_YYYY_HH_MM_24
import com.kurio.tetsuya.movie.compose.core.DateConstants.DD_MMMM_YYYY_HH_MM_A_12
import com.kurio.tetsuya.movie.compose.core.DateConstants.MMM_DD_YYYY_HH_M_A
import com.kurio.tetsuya.movie.compose.core.DateConstants.YYYY
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private val MMM_DD_YYYY_HH_M_A_PATTERN = SimpleDateFormat(MMM_DD_YYYY_HH_M_A, Locale.ENGLISH)
private val DD_MMMM_YYYY_HH_MM_A_12_PATTERN =
    SimpleDateFormat(DD_MMMM_YYYY_HH_MM_A_12, Locale.ENGLISH)
private val DD_MMMM_YYYY_HH_MM_24_PATTERN = SimpleDateFormat(DD_MMMM_YYYY_HH_MM_24, Locale.ENGLISH)
private val DD_MMMM_HH_MM_24_PATTERN = SimpleDateFormat(DD_MMMM_HH_MM_24, Locale.ENGLISH)
private val DD_MMMM_HH_MM_A_12_PATTERN = SimpleDateFormat(DD_MMMM_HH_MM_A_12, Locale.ENGLISH)
private val YYYY_PATTERN = SimpleDateFormat(YYYY, Locale.ENGLISH)
val currentYear = Calendar.getInstance().get(Calendar.YEAR)

fun convertDDMMMMYYYYHHMMA(timeString: String): String {
    return try {
        val parser = MMM_DD_YYYY_HH_M_A_PATTERN
        val simpleDateFormat = DD_MMMM_YYYY_HH_MM_A_12_PATTERN
        val date = parser.parse(timeString)
        val formatted = date?.let { simpleDateFormat.format(it) }
        formatted.toString()
    } catch (e: Exception) {
        ""
    }
}

fun convertDDMMMMYYYYHHMM(timeString: String): String {
    return try {
        val parser = MMM_DD_YYYY_HH_M_A_PATTERN
        val simpleDateFormat = DD_MMMM_YYYY_HH_MM_24_PATTERN
        val date = parser.parse(timeString)
        val formatted = date?.let { simpleDateFormat.format(it) }
        formatted.toString()
    } catch (e: Exception) {
        ""
    }
}

fun convertDDMMMMHHMMA(timeString: String): String {
    return try {
        val parser = MMM_DD_YYYY_HH_M_A_PATTERN
        val simpleDateFormat = DD_MMMM_HH_MM_A_12_PATTERN
        val date = parser.parse(timeString)
        val formatted = date?.let { simpleDateFormat.format(it) }
        formatted.toString()
    } catch (e: Exception) {
        ""
    }
}

fun convertDDMMMMHHMM(timeString: String): String {
    return try {
        val parser = MMM_DD_YYYY_HH_M_A_PATTERN
        val simpleDateFormat = DD_MMMM_HH_MM_24_PATTERN
        val date = parser.parse(timeString)
        val formatted = date?.let { simpleDateFormat.format(it) }
        formatted.toString()
    } catch (e: Exception) {
        ""
    }
}