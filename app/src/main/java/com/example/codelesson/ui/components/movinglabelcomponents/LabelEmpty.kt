package com.example.codelesson.ui.components.movinglabelcomponents

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.codelesson.util.PracticeViewModel

@Composable
fun LabelEmpty(practiceViewModel: PracticeViewModel) {
    var d = LocalDensity.current
    OutlinedCard(
        modifier = Modifier
            .width(135.dp)
            .height(40.dp)
            .onGloballyPositioned {
                practiceViewModel.getEmptyLabelPosition(it.positionInRoot()/d.density)
            }
    ) {

    }
}