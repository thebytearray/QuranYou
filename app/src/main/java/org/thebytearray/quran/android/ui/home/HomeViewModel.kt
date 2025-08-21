package org.thebytearray.quran.android.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.thebytearray.quran.android.data.model.Surah
import org.thebytearray.quran.android.domain.repository.SurahRepository
import org.thebytearray.quran.android.domain.repository.UserPreferencesRepository
import org.thebytearray.quran.android.domain.usecase.GetSurahListUseCase
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurahListUseCase: GetSurahListUseCase,
) : ViewModel() {

    private val _surahList = MutableStateFlow<List<Surah>>(emptyList())
    val surahList: StateFlow<List<Surah>> get() = _surahList.asStateFlow()


    init {
        loadSurahList()
    }

    private fun loadSurahList() {
        viewModelScope.launch {
            _surahList.value = getSurahListUseCase()
        }
    }





}