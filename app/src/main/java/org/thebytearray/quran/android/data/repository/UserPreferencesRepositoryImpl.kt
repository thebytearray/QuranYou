package org.thebytearray.quran.android.data.repository

import kotlinx.coroutines.flow.Flow
import org.thebytearray.quran.android.data.local.UserPreferencesDataSource
import org.thebytearray.quran.android.domain.model.ArabicTextType
import org.thebytearray.quran.android.domain.model.Reciter
import org.thebytearray.quran.android.domain.model.Translation
import org.thebytearray.quran.android.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataSource: UserPreferencesDataSource
) : UserPreferencesRepository {
    override val selectedReciter: Flow<Reciter>
        get() = dataSource.reciterFlow
    override val selectedTranslation: Flow<Translation>
        get() = dataSource.translationFlow
    override val selectedArabicType: Flow<ArabicTextType>
        get() = dataSource.arabicTextTypeFlow

    override suspend fun saveReciter(reciter: Reciter) {
        dataSource.saveReciter(reciter)
    }

    override suspend fun saveTranslation(lang: Translation) {
        dataSource.saveTranslation(lang)
    }

    override suspend fun saveArabicType(type: ArabicTextType) {
        dataSource.saveArabicType(type)
    }


}