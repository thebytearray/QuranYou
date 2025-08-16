package org.thebytearray.quran.android.ui.surah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.thebytearray.quran.android.domain.model.ArabicTextType
import org.thebytearray.quran.android.domain.model.Reciter
import org.thebytearray.quran.android.domain.model.Translation
import org.thebytearray.quran.android.domain.repository.UserPreferencesRepository
import javax.inject.Inject


@HiltViewModel
class SurahViewModel @Inject constructor(
    private val repository: UserPreferencesRepository
) : ViewModel() {

    val selectedReciter: StateFlow<Reciter> = repository.selectedReciter.stateIn(
        viewModelScope, SharingStarted.Lazily, Reciter.AL_FASY
    )

    val translationLanguage: StateFlow<Translation> = repository.selectedTranslation.stateIn(
        viewModelScope, SharingStarted.Lazily, Translation.ENGLISH
    )

    val arabicTextType: StateFlow<ArabicTextType> = repository.selectedArabicType.stateIn(
        viewModelScope, SharingStarted.Lazily, ArabicTextType.NO_TASHKEEL
    )


}