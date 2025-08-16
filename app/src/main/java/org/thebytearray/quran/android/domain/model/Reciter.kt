package org.thebytearray.quran.android.domain.model

enum class Reciter(val reciter: String) {
    AL_FASY("mraa"), AL_SHATRI("abas"), AL_QATAMI("naq"), AL_DOSARI("yad"), AR_RIFAI("har");

    companion object {
        fun fromName(name: String) =
            entries.find { it.name.equals(name, ignoreCase = true) } ?: AL_FASY
    }
}