package org.thebytearray.quran.android.ui.surah

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import org.thebytearray.quran.android.domain.model.Reciter
import org.thebytearray.quran.android.domain.model.Translation
import org.thebytearray.quran.android.ui.component.AudioPlayerCard
import org.thebytearray.quran.android.ui.component.AudioState
import org.thebytearray.quran.android.ui.component.ChapterVerseItem
import org.thebytearray.quran.android.ui.component.SurahHeaderCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahScreen(
    navController: NavHostController,
    onNavigationClick: () -> Unit,
    viewModel: SurahViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val surahIndex = navController.currentBackStackEntry?.arguments?.getString("index")?.toIntOrNull()
    val selectedChapter by viewModel.selectedChapter.collectAsState()
    val selectedTranslation by viewModel.translationLanguage.collectAsState()
    val selectedReciter by viewModel.selectedReciter.collectAsState()

    var audioState by remember { mutableStateOf(AudioState.IDLE) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var currentPosition by remember { mutableStateOf(0) }
    var duration by remember { mutableStateOf(0) }

    LaunchedEffect(surahIndex) {
        surahIndex?.let {
            viewModel.fetchSurah(it)
        }
    }

    LaunchedEffect(audioState) {
        if (audioState == AudioState.PLAYING) {
            while (audioState == AudioState.PLAYING && mediaPlayer?.isPlaying == true) {
                currentPosition = mediaPlayer?.currentPosition ?: 0
                duration = mediaPlayer?.duration ?: 0
                delay(1000)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    fun getAudioUrl(chapter: org.thebytearray.quran.android.data.model.Chapter, reciter: Reciter): String? {
        return when (reciter) {
            Reciter.AL_FASY -> chapter.audio.mraa.url
            Reciter.AL_SHATRI -> chapter.audio.abas.url
            Reciter.AL_QATAMI -> chapter.audio.naq.url
            Reciter.AL_DOSARI -> chapter.audio.yad.url
            Reciter.AR_RIFAI -> chapter.audio.har.url
        }
    }

    fun playAudio(chapter: org.thebytearray.quran.android.data.model.Chapter) {
        val audioUrl = getAudioUrl(chapter, selectedReciter) ?: return
        
        if (audioState == AudioState.PLAYING) {
            mediaPlayer?.pause()
            audioState = AudioState.PAUSED
        } else if (audioState == AudioState.PAUSED) {
            mediaPlayer?.start()
            audioState = AudioState.PLAYING
        } else {
            audioState = AudioState.LOADING
            
            try {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    
                    setDataSource(audioUrl)
                    setOnPreparedListener {
                        start()
                        audioState = AudioState.PLAYING
                        duration = duration
                    }
                    
                    setOnCompletionListener {
                        audioState = AudioState.IDLE
                        currentPosition = 0
                    }
                    
                    setOnErrorListener { _, _, _ ->
                        audioState = AudioState.ERROR
                        true
                    }
                    
                    prepareAsync()
                }
            } catch (e: Exception) {
                audioState = AudioState.ERROR
            }
        }
    }

    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        audioState = AudioState.IDLE
        currentPosition = 0
        duration = 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = selectedChapter?.surahName ?: "Loading...",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigationClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        selectedChapter?.let { chapter ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    SurahHeaderCard(
                        surahName = chapter.surahName,
                        surahNameArabic = chapter.surahNameArabic,
                        surahNameTranslation = chapter.surahNameTranslation,
                        revelationPlace = chapter.revelationPlace,
                        totalAyah = chapter.totalAyah.toInt()
                    )
                }

                item {
                    AudioPlayerCard(
                        reciterName = getReciterDisplayName(selectedReciter),
                        audioState = audioState,
                        currentPosition = currentPosition,
                        duration = duration,
                        onPlayClick = { playAudio(chapter) },
                        onStopClick = { stopAudio() },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                val arabicVerses = chapter.arabic1
                val translationVerses = getTranslationVerses(chapter, selectedTranslation)

                itemsIndexed(arabicVerses) { index, arabicText ->
                    val translationText = translationVerses.getOrNull(index) ?: ""
                    
                    ChapterVerseItem(
                        verseNumber = index + 1,
                        arabicText = arabicText,
                        translationText = translationText
                    )
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

private fun getReciterDisplayName(reciter: Reciter): String {
    return when (reciter) {
        Reciter.AL_FASY -> "Mishary Rashid Al Afasy"
        Reciter.AL_SHATRI -> "Abu Bakr Al Shatri"
        Reciter.AL_QATAMI -> "Nasser Al Qatami"
        Reciter.AL_DOSARI -> "Yasser Al Dosari"
        Reciter.AR_RIFAI -> "Hani Ar Rifai"
    }
}

private fun getTranslationVerses(chapter: org.thebytearray.quran.android.data.model.Chapter, translation: Translation): List<String> {
    return when (translation) {
        Translation.ENGLISH -> chapter.english
        Translation.BENGALI -> chapter.bengali
        Translation.URDU -> chapter.urdu
        Translation.UZBEK -> chapter.uzbek
    }
}

@Preview
@Composable
private fun PreviewSurahScreen() {
    SurahScreen(
        navController = rememberNavController(),
        onNavigationClick = {}
    )
}