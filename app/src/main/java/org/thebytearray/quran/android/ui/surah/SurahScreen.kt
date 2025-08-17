package org.thebytearray.quran.android.ui.surah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahScreen(onNavigationClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text(text = "Chapter - Al Baqrah")
        }, navigationIcon = {
            IconButton(onClick = { onNavigationClick() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)

            }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(text = "Hello This is Surah Chapter Screen")
            Text(text = "Hello This is Surah Chapter Screen")
            Text(text = "Hello This is Surah Chapter Screen")
            Text(text = "Hello This is Surah Chapter Screen")
            Text(text = "Hello This is Surah Chapter Screen")
            Text(text = "Hello This is Surah Chapter Screen")
            Text(text = "Hello This is Surah Chapter Screen")
            Text(text = "Hello This is Surah Chapter Screen")
            Text(text = "Hello This is Surah Chapter Screen")

        }
    }


}
