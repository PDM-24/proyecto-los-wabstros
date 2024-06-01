package com.example.codelesson.ui.components

import androidx.compose.ui.geometry.Rect
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asComposePaint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.VectorGroup
import androidx.compose.ui.graphics.vector.VectorNode
import androidx.compose.ui.graphics.vector.VectorPath
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun NeonIcon(
    drawable: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = Color.Black,
    shadowXOffset: Dp = 0.dp,
    shadowYOffset: Dp = 0.dp,
    shadowRadius: Dp = 0.dp
){
    val vectors = mutableListOf<PathNode>()
    fun recursiveWalkVectors(vectorNode: VectorNode){
        when(vectorNode){
            is VectorGroup -> vectorNode.forEach{recursiveWalkVectors(it)}
            is VectorPath -> vectors.addAll(vectorNode.pathData)
        }
    }

    recursiveWalkVectors(drawable.root)
    var path = PathParser().addPathNodes(vectors).toPath()

    val dx: Float
    val dy: Float
    val radiusInPx: Float
    with(LocalDensity.current){
        dx = shadowXOffset.toPx()
        dy = shadowYOffset.toPx()
        radiusInPx = shadowRadius.toPx()
    }

    val paint = remember {
        Paint().asFrameworkPaint()
    }

    fun adjust(path: Path, availableSpace: Rect){
        val pathRect = path.getBounds()
        val(translateX, translateY) = availableSpace.left - pathRect.left to availableSpace.top - pathRect.top
        val scale = availableSpace.let {
            minOf<Float>(it.height / pathRect.height, it.width / pathRect.width)
        }
        path.translate(Offset(translateX, translateY))
        path.transform(Matrix().apply { scale(scale,scale) })
    }

    Icon(
        modifier = modifier
            .drawBehind {
                adjust(path, drawContext.size.toRect())
                drawIntoCanvas {
                    paint.color = tint.toArgb()
                    paint.setShadowLayer(
                        radiusInPx,
                        dx,
                        dy,
                        tint
                            .copy(alpha = 0.9f)
                            .toArgb()
                    )
                    it.drawPath(path, paint.asComposePaint())
                }
            },
        imageVector = drawable,
        contentDescription = "icon-with-shadow",
        tint = Color.Transparent
    )
}