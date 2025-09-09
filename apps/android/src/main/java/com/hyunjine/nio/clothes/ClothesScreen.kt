package com.hyunjine.nio.clothes

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hyunjine.nio.clothes.model.ClothesItemModel

@Composable
fun ClothesScreen(
    modifier: Modifier = Modifier,
) {
    ClothesGrid(modifier = modifier)
}

@Composable
fun ClothesGrid(
    modifier: Modifier = Modifier,
    viewModel: ClothesViewModel = viewModel()
) {

    val items by viewModel.clothes.collectAsStateWithLifecycle()
    ClothesGrid(
        modifier = modifier,
        clothes = items
    )
}

@Composable
fun ClothesGrid(
    modifier: Modifier = Modifier,
    clothes: List<ClothesItemModel>
) {
    Box {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = modifier
        ) {
            items(clothes) { model ->
                ClothesItem(model)
            }
        }
        FloatingActionButton(
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp)
            ,
            onClick = {

            }
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
fun ClothesItem(
    model: ClothesItemModel
) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(model.link)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(
            text = model.description,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ClothesPreview() {
    val dummy = List(
        10
    ) {
        val link = "https://picsum.photos/400/200"
        ClothesItemModel(link = link, thumbnail = link, description = "테스트입니다.")
    }
    ClothesGrid(
        clothes = dummy
    )
}