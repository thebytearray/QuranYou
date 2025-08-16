package org.thebytearray.quran.android.data.remote

import org.thebytearray.quran.android.data.model.Chapter
import org.thebytearray.quran.android.data.model.Surah
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/surah.json")
    suspend fun getSurahList(): List<Surah>


    @GET("api/{chapterNumber}.json")
    suspend fun getChapter(
        @Path("chapterNumber") chapterNumber: Int
    ): Chapter


}