package org.thebytearray.quran.android.domain.repository

import kotlinx.coroutines.flow.Flow
import org.thebytearray.quran.android.domain.model.ArabicTextType
import org.thebytearray.quran.android.domain.model.Reciter
import org.thebytearray.quran.android.domain.model.Translation

interface UserPreferencesRepository {
    val selectedReciter: Flow<Reciter>
    val selectedTranslation: Flow<Translation>
    val selectedArabicType: Flow<ArabicTextType>
    suspend fun saveReciter(reciter: Reciter)
    suspend fun saveTranslation(lang: Translation)
    suspend fun saveArabicType(type: ArabicTextType)
}