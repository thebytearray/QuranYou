package org.thebytearray.quran.android.data.model

import com.google.gson.annotations.SerializedName

data class Chapter(
    val surahName: String,
    val surahNameArabic: String,
    val surahNameArabicLong: String,
    val surahNameTranslation: String,
    val revelationPlace: String,
    val totalAyah: Long,
    val surahNo: Long,
    val audio: Audio,
    val english: List<String>,
    val arabic1: List<String>,
    val arabic2: List<String>,
    val bengali: List<String>,
    val urdu: List<String>,
    val uzbek: List<String>,
)

data class Audio(
    //Mishary Rashid Al Afasy
    @SerializedName("1") val mraa: AudioItem,
    //Abu Bakr Al Shatri
    @SerializedName("2") val abas: AudioItem,
    //Nasser Al Qatami
    @SerializedName("3") val naq: AudioItem,
    //Yasser Al Dosari
    @SerializedName("4") val yad: AudioItem,
    //Hani Ar Rifai
    @SerializedName("5") val har: AudioItem,
)

data class AudioItem(
    val reciter: String,
    val url: String,
    val originalUrl: String,
)

