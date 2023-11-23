package com.example.myflix.home.impl.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myflix.home.impl.R

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderSection(
            name = "Welcome",
            modifier = Modifier.fillMaxWidth()
                .padding(top = 40.dp)
                .padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun HeaderSection(
    name: String,
    modifier: Modifier
) {

    val titleSpan = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append(stringResource(id = R.string.hello_text))
        }
        append("")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append(name)
        }
    }

    Row(modifier = modifier) {
        Column(modifier = Modifier.weight(1f)) {
           Text(text = titleSpan, fontSize = 24.sp, color = Color.White)
        }
    }
}