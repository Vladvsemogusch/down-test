package cc.anisimov.vladyslav.downtest.ui.base.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun UrlImage(
    url: String?,
    modifier: Modifier = Modifier,
    scale: ContentScale = ContentScale.Crop,
    shape: Shape = RoundedCornerShape(0.dp),
    colorFilter: ColorFilter? = null
) {
    SubcomposeAsyncImage(
        contentScale = scale,
        contentDescription = null,
        modifier = modifier.clip(shape),
        colorFilter = colorFilter,
        model = url,
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            CircularProgressIndicator()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}