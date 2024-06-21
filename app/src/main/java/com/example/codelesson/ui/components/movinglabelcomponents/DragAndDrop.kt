package com.example.codelesson.ui.components.movinglabelcomponents
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.codelesson.util.PracticeViewModel

internal val LocalDragTargetInfo = compositionLocalOf { DragAndDrop() }

@Composable
fun <T> DragTarget(
    modifier: Modifier = Modifier,
    dataDrop: T,
    viewModel: PracticeViewModel,
    content: @Composable (() -> Unit),
) {
    val isDragging = remember {
        mutableStateOf(false)
    }

    var currentPosition by remember {
        mutableStateOf(Offset.Zero)
    }

    val currentState = LocalDragTargetInfo.current

    Box(
        modifier = modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(Offset.Zero)
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        viewModel.startDragging()
                        currentState.dataDrop = dataDrop
                        isDragging.value = true
                        currentState.isDragging = true
                        currentState.dragPosition = currentPosition + it
                        currentState.draggableComposable = content
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)
                    },
                    onDragEnd = {
                        viewModel.stopDragging()
                        currentState.dragOffset = Offset.Zero
                        currentState.isDragging = false
                        isDragging.value = false
                    },
                    onDragCancel = {
                        viewModel.stopDragging()
                        currentState.dragOffset = Offset.Zero
                        currentState.isDragging = false
                        isDragging.value = false
                    }
                )
            }
    ) {
        if (!isDragging.value) {
            content()
        }
    }
}

@Composable
fun <T> DropItem(
    modifier: Modifier,
    content: @Composable() (BoxScope.(isInBound: Boolean, data: T?) -> Unit),
) {
    val dragInfo = LocalDragTargetInfo.current
    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isCurrentDropTarget by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .width(135.dp)
            .height(40.dp)
            .onGloballyPositioned {
                it
                    .boundsInWindow()
                    .let { rect ->
                        isCurrentDropTarget = rect.contains(dragPosition + dragOffset)
                    }
            }
    ) {
        val data = if(isCurrentDropTarget && !dragInfo.isDragging) dragInfo.dataDrop as T? else null
        content(isCurrentDropTarget, data)
    }
}

@Composable
fun DragableScreen(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val state = remember {
        DragAndDrop()
    }

    CompositionLocalProvider(
        LocalDragTargetInfo provides state
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            content()
            if (state.isDragging) {
                var targetSize by remember {
                    mutableStateOf(IntSize.Zero)
                }

                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            val offset = (state.dragPosition + state.dragOffset)
                            scaleX = 1.3f
                            scaleY = 1.3f
                            alpha = if (targetSize == IntSize.Zero) 0f else .9f
                            translationX = offset.x.minus(targetSize.width / 2)
                            translationY = offset.y.minus(targetSize.height / 2)
                        }
                        .onGloballyPositioned {
                            targetSize = it.size
                        }
                ) {
                    state.draggableComposable?.invoke()
                }
            }
        }
    }
}


class DragAndDrop {
    var isDragging: Boolean by mutableStateOf(false)
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
    var dataDrop by mutableStateOf<Any?>(null)
}