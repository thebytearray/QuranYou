package org.thebytearray.quran.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.thebytearray.quran.android.data.model.Surah

@Composable
fun SurahItem(modifier: Modifier = Modifier, index: Int, surah: Surah) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {


            Box(
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.CenterVertically)
                    .clip(
                        RoundedCornerShape(size = 8.dp)
                    )
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(text = index.toString(), fontSize = 12.sp)
            }

            Text(
                text = "${surah.name} • ${surah.totalAyah} Chapters",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                fontSize = 12.sp
            )


            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = surah.revelationPlace,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                fontSize = 12.sp
            )
            Text(
                text = surah.nameArabic,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                fontSize = 12.sp
            )


        }


    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewSurahItem() {
    SurahItem(
        index = 2, surah = Surah(
            name = "Al-Baqara",
            nameArabic = "البقرة",
            nameArabicLong = "سورة البقرة",
            nameTranslation = "The Cow",
            revelationPlace = "Madina",
            totalAyah = 286
        )
    )
}