package com.example.pix.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.pix.R
import com.example.pix.domain.entity.Picture

@Composable
fun PicturesScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        PicturesScreenContent(
            modifier = Modifier.padding(paddingValues = it),
            onImageClick = { },
            onQueryChange = { }
        )
    }
}

@Composable
fun PicturesScreenContent(
    modifier: Modifier,
    onImageClick: (pic: Picture) -> Unit,
    onQueryChange: (String) -> Unit) {
    Column(modifier = modifier) {

        SearchTextField(onQueryChange)
        PictureList(
            listOf(
                Picture("url", "title"),
                Picture("url", "title"),
                Picture("url", "title"),
            ),
            modifier = Modifier.padding(horizontal = paddingMedium),
            onClick = onImageClick
            )
    }
}

@Composable
fun SearchTextField(onValueChange: (String) -> Unit) {
    var value by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
            onValueChange(value)
        },
        modifier = Modifier
            .padding(paddingMedium)
            .fillMaxWidth(),
        textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
        label = { Text("Search", style = TextStyle(fontSize = 16.sp, color = Color.Gray)) },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        trailingIcon = { Icon(painterResource(R.drawable.search), null, tint = Color.Gray) }
    )
}

@Composable
fun PictureList(
    pictures: List<Picture>,
    modifier: Modifier = Modifier,
    onClick: (pic: Picture) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(paddingMedium),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pictures.size) { index ->
            PictureCard(pictures[index], onClick = onClick)
        }
    }
}

@Composable
fun PictureCard(picture: Picture, modifier: Modifier = Modifier, onClick: (pic: Picture) -> Unit) {
    ElevatedCard(
        onClick = { onClick(picture) },
        modifier = modifier.aspectRatio(1f),
        shape = CardDefaults.elevatedShape
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painterResource(R.drawable.image),
                null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
//            LoadImage(picture)
            TextOnImage(picture.title)
        }
    }
}

@Composable
fun TextOnImage(text: String) {
    Text(
        text, style = TextStyle(
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        ),
        modifier = Modifier
            .padding(start = paddingMedium, bottom = paddingMedium)
            .background(
                Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = paddingSmall)
    )
}

@Composable
fun LoadImage(picture: Picture) {
    AsyncImage(
        model = picture.url,
        contentDescription = picture.title,
        modifier = Modifier.fillMaxSize(),
        placeholder = painterResource(R.drawable.image),
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview(showBackground = true)
fun SearchContentPreview() {
    PicturesScreenContent(Modifier.fillMaxSize(), {}, {})
}

//@Composable
//@Preview(showBackground = true)
//fun PictureListPreview() {
//    PictureList(
//        listOf(
//            Picture("url", "title"),
//            Picture("url", "title"),
//            Picture("url", "title"),
//            Picture("url", "title"),
//            Picture("url", "title"),
//        )
//    ) { }
//}

//@Composable
//@Preview(showBackground = true)
//fun PictureCardPreview() {
//    PictureCard(Picture("url", "title")) {}
//}