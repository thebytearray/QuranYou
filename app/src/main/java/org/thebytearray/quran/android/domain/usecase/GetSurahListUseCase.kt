package org.thebytearray.quran.android.domain.usecase

import org.thebytearray.quran.android.data.model.Surah
import org.thebytearray.quran.android.domain.repository.SurahRepository
import javax.inject.Inject

class GetSurahListUseCase @Inject constructor(
    private val surahRepository: SurahRepository
) {


    suspend operator fun invoke(): List<Surah> {
        return surahRepository.getSurahList()
    }


}