package com.example.pix.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pix.R
import com.example.pix.domain.entity.Picture

@Composable
fun PictureScreen(picture: Picture = Picture("url", "Test")) {
    Scaffold {
        PictureScreenContent(
            modifier = Modifier.padding(paddingValues = it),
            picture = picture
        )
    }
}

@Composable
fun PictureScreenContent(modifier: Modifier, picture: Picture) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Column(Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()) {
                ZoomablePictureBox(url = picture.url, Modifier)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = paddingMedium),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                picture.title, style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun ZoomablePictureBox(url: String, modifier: Modifier = Modifier) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val state = rememberTransformableState { zoomChange, offsetChange, _ ->
            scale = (scale * zoomChange).coerceIn(1f, 3f)

            val extraWidth = (scale - 1) * constraints.maxWidth
            val extraHeight = (scale - 1) * constraints.maxHeight

            val maxX = (extraWidth / 2).coerceAtLeast(0f)
            val maxY = (extraHeight / 2).coerceAtLeast(0f)

            offset = Offset(
                x = (offset.x + scale * offsetChange.x).coerceIn(-maxX, maxX),
                y = (offset.y + scale * offsetChange.y).coerceIn(-maxY, maxY)
            )
        }

        Image(
            painterResource(R.drawable.image),
            null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(state),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PictureScreenPreview() {
    PictureScreenContent(Modifier.fillMaxSize(), Picture("url", "title"))
}
