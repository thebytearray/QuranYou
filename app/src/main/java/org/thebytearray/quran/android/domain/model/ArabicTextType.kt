package org.thebytearray.quran.android.domain.model

enum class ArabicTextType(type: String) {
    TASHKEEL("tashkeel"), NO_TASHKEEL("no_tashkeel");

    companion object {
        fun fromType(type: String) =
            entries.find { it.name.equals(type, ignoreCase = true) } ?: NO_TASHKEEL
    }
}