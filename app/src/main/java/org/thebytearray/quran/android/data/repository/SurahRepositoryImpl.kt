package org.thebytearray.quran.android.data.repository

import org.thebytearray.quran.android.data.model.Chapter
import org.thebytearray.quran.android.data.model.Surah
import org.thebytearray.quran.android.data.remote.ApiService
import org.thebytearray.quran.android.domain.repository.SurahRepository
import javax.inject.Inject

class SurahRepositoryImpl @Inject constructor(
    private val api: ApiService
) : SurahRepository {
    override suspend fun getSurahList(): List<Surah> {
        return api.getSurahList()
    }

    override suspend fun getChapter(number: Int): Chapter {
        return api.getChapter(chapterNumber = number)
    }
}