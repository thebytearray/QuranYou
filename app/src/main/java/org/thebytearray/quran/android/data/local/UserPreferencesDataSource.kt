package org.thebytearray.quran.android.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.thebytearray.quran.android.domain.model.ArabicTextType
import org.thebytearray.quran.android.domain.model.Reciter
import org.thebytearray.quran.android.domain.model.Translation

import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {


    private object Keys {
        val PREF_RECITER = stringPreferencesKey("selected_reciter")
        val PREF_TRANSLATION = stringPreferencesKey("translation_language")
        val PREF_ARABIC_TYPE = stringPreferencesKey("arabic_type")
    }


    val reciterFlow: Flow<Reciter> = dataStore.data.map { prefs ->
        val name: String? = prefs[Keys.PREF_RECITER]
        Reciter.fromName(name ?: "")
    }

    val translationFlow: Flow<Translation> = dataStore.data.map { prefs ->
        val lang: String? = prefs[Keys.PREF_TRANSLATION]
        Translation.fromLang(lang ?: "en")
    }

    val arabicTextTypeFlow: Flow<ArabicTextType> = dataStore.data.map { prefs ->
        val type: String? = prefs[Keys.PREF_ARABIC_TYPE]
        ArabicTextType.fromType(type ?: "no_tashkeel")

    }

    suspend fun saveReciter(reciter: Reciter) {
        dataStore.edit { prefs ->
            prefs[Keys.PREF_RECITER] = reciter.name
        }
    }


    suspend fun saveTranslation(translation: Translation) {
        dataStore.edit { prefs ->
            prefs[Keys.PREF_TRANSLATION] = translation.name
        }
    }


    suspend fun saveArabicType(type: ArabicTextType) {
        dataStore.edit { prefs ->
            prefs[Keys.PREF_ARABIC_TYPE] = type.name
        }
    }


}