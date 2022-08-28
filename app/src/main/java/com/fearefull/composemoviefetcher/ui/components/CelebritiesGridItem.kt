package com.fearefull.composemoviefetcher.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.fearefull.composemoviefetcher.R
import com.fearefull.composemoviefetcher.data.model.other.Celebrity
import com.fearefull.composemoviefetcher.data.model.remote.GenderType
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants

@Composable
fun CelebritiesGridItem(
    item: Celebrity,
    onItemClicked: (item: Celebrity) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(ThemeConstants.HALF_PADDING.dp)
            .clickable { onItemClicked(item) }
    ) {
        CelebritiesGridItemThumbnail(item.profilePath, item.name, item.gender)
        Spacer(modifier = Modifier.height(ThemeConstants.PADDING.dp))
        Text(item.name)
        Spacer(modifier = Modifier.height(ThemeConstants.HALF_PADDING.dp))
    }
}

@Composable
private fun CelebritiesGridItemThumbnail(
    url: String?,
    description: String?,
    gender: GenderType
) {
    val painter = rememberAsyncImagePainter(
        model = url,
        error = rememberVectorPainter(Icons.Filled.BrokenImage),
        placeholder = painterResource(id = if (gender == GenderType.FEMALE) R.mipmap.female else R.mipmap.male)
    )
    val colorFilter = when (painter.state) {
        is AsyncImagePainter.State.Loading, is AsyncImagePainter.State.Error -> ColorFilter.tint(MaterialTheme.colors.primary)
        else -> null
    }
    Image(
        painter = painter,
        contentDescription = description,
        colorFilter = colorFilter,
        modifier = Modifier
            .aspectRatio(ThemeConstants.MOVIES_GRID_ITEM_THUMBNAIL_ASPECT_RATIO, true)
            .clip(RoundedCornerShape(ThemeConstants.ROUNDED_CORNER.dp))
        ,
        contentScale = ContentScale.Crop
    )
}