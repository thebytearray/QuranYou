package org.thebytearray.quran.android.domain.repository

import org.thebytearray.quran.android.data.model.Chapter
import org.thebytearray.quran.android.data.model.Surah

interface SurahRepository {
    suspend fun getSurahList(): List<Surah>
    suspend fun getChapter(number: Int): Chapter
}