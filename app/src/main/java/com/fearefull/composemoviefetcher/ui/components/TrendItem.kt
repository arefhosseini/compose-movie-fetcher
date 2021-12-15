package com.fearefull.composemoviefetcher.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.fearefull.composemoviefetcher.R
import com.fearefull.composemoviefetcher.data.model.other.Trend
import com.fearefull.composemoviefetcher.util.constants.ThemeConstants

@Composable
fun TrendItem(
    item: Trend,
    onItemClicked: (item: Trend) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(ThemeConstants.HALF_PADDING.dp)
            .width(ThemeConstants.TREND_THUMBNAIL_WIDTH.dp)
            .clickable { onItemClicked(item) }
    ) {
        TrendItemThumbnail(item.posterPath, item.title)
        Spacer(modifier = Modifier.height(ThemeConstants.PADDING.dp))
        Text(item.title)
        Spacer(modifier = Modifier.height(ThemeConstants.HALF_PADDING.dp))
        Text(
            item.releaseDate,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(ThemeConstants.HALF_PADDING.dp))
    }
}

@Composable
fun TrendItemThumbnail(
    url: String?,
    description: String?
) {
    val painter: Painter = url?.let { rememberImagePainter(
        data = url
    ) } ?: run { painterResource(
        R.mipmap.movie
    ) }
    Image(
        painter = painter,
        contentDescription = description,
        modifier = Modifier
            .widthIn(ThemeConstants.TREND_THUMBNAIL_WIDTH.dp, ThemeConstants.TREND_THUMBNAIL_WIDTH.dp)
            .aspectRatio(ThemeConstants.TREND_THUMBNAIL_ASPECT_RATIO, true)
            .clip(RoundedCornerShape(ThemeConstants.ROUNDED_CORNER.dp))
        ,
        contentScale = ContentScale.Crop
    )
}