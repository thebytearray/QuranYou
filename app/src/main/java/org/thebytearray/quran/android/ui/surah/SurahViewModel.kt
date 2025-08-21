package org.thebytearray.quran.android.ui.surah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.thebytearray.quran.android.data.model.Chapter
import org.thebytearray.quran.android.data.model.Surah
import org.thebytearray.quran.android.domain.model.ArabicTextType
import org.thebytearray.quran.android.domain.model.Reciter
import org.thebytearray.quran.android.domain.model.Translation
import org.thebytearray.quran.android.domain.repository.SurahRepository
import org.thebytearray.quran.android.domain.repository.UserPreferencesRepository
import javax.inject.Inject


@HiltViewModel
class SurahViewModel @Inject constructor(
    private val repository: UserPreferencesRepository,
    private val surahRepository: SurahRepository
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


    //
    private val _selectedChapter = MutableStateFlow<Chapter?>(null)
    val selectedChapter get() = _selectedChapter


    fun fetchSurah(index: Int) {
        viewModelScope.launch {
            _selectedChapter.value = surahRepository.getChapter(index)
        }
    }


}