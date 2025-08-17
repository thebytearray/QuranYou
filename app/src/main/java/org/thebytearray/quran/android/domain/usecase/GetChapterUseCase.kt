package org.thebytearray.quran.android.domain.usecase

import org.thebytearray.quran.android.data.model.Chapter
import org.thebytearray.quran.android.domain.repository.SurahRepository
import javax.inject.Inject

class GetChapterUseCase @Inject constructor(
    private val surahRepository: SurahRepository
) {


    suspend operator fun invoke(number: Int): Chapter {
        return surahRepository.getChapter(number = number)
    }


}