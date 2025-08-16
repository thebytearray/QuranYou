package org.thebytearray.quran.android.domain.model

enum class Translation(lang: String) {
    BENGALI("bn"), ENGLISH("en"), URDU("ur"), UZBEK("uz");

    companion object {
        fun fromLang(lang: String): Translation =
            entries.find { it.name.equals(lang, ignoreCase = true) } ?: ENGLISH

    }
}