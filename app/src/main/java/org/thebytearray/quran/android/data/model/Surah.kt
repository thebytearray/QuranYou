package org.thebytearray.quran.android.data.model

import com.google.gson.annotations.SerializedName

data class Surah(
    val index: Int? = null,
    @SerializedName("surahName")
    val name: String,

    @SerializedName("surahNameArabic")
    val nameArabic: String,

    @SerializedName("surahNameArabicLong")
    val nameArabicLong: String,

    @SerializedName("surahNameTranslation")
    val nameTranslation: String,

    val revelationPlace: String,
    val totalAyah: Int
)
