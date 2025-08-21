package org.thebytearray.quran.android.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.thebytearray.quran.android.domain.model.ArabicTextType
import org.thebytearray.quran.android.domain.model.Reciter
import org.thebytearray.quran.android.domain.model.Translation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    onNavigationClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val selectedReciter by viewModel.selectedReciter.collectAsState()
    val selectedTranslation by viewModel.selectedTranslation.collectAsState()
    val selectedArabicType by viewModel.selectedArabicType.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
        ) {
            item {
                SettingsSection(
                    title = "Translation Language",
                    icon = Icons.Default.Language
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Translation.entries.forEach { translation ->
                            SettingsOptionItem(
                                text = getTranslationDisplayName(translation),
                                isSelected = selectedTranslation == translation,
                                onSelect = { viewModel.updateTranslation(translation) }
                            )
                        }
                    }
                }
            }

            item {
                SettingsSection(
                    title = "Arabic Text Style",
                    icon = Icons.Default.TextFields
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ArabicTextType.entries.forEach { arabicType ->
                            SettingsOptionItem(
                                text = getArabicTextTypeDisplayName(arabicType),
                                isSelected = selectedArabicType == arabicType,
                                onSelect = { viewModel.updateArabicTextType(arabicType) }
                            )
                        }
                    }
                }
            }

            item {
                SettingsSection(
                    title = "Audio Reciter",
                    icon = Icons.Default.RecordVoiceOver
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Reciter.entries.forEach { reciter ->
                            SettingsOptionItem(
                                text = getReciterDisplayName(reciter),
                                isSelected = selectedReciter == reciter,
                                onSelect = { viewModel.updateReciter(reciter) }
                            )
                        }
                    }
                }
            }

            item {
                AppInfoCard()
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    icon: ImageVector,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            content()
        }
    }
}

@Composable
private fun SettingsOptionItem(
    text: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = onSelect
            )
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun AppInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "QuranYou",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            
            Text(
                text = "Your companion for reading the Holy Quran",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Version 1.0",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

private fun getTranslationDisplayName(translation: Translation): String {
    return when (translation) {
        Translation.ENGLISH -> "English"
        Translation.BENGALI -> "বাংলা (Bengali)"
        Translation.URDU -> "اردو (Urdu)"
        Translation.UZBEK -> "O'zbek (Uzbek)"
    }
}

private fun getArabicTextTypeDisplayName(arabicType: ArabicTextType): String {
    return when (arabicType) {
        ArabicTextType.TASHKEEL -> "With Tashkeel (Diacritics)"
        ArabicTextType.NO_TASHKEEL -> "Without Tashkeel"
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