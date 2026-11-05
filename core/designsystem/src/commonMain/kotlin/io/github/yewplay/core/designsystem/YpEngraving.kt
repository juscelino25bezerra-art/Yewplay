/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package io.github.yewplay.core.designsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

/**
 * A yew engraving (proposal, p. 9): the lines in ivory over graphite, or in ink over paper in
 * the light theme, and the fruit always in ruby. Each engraving comes as two vectors of the
 * same size: the lines (tinted here) and the fruit (with its own colors).
 */
@Composable
fun YpEngraving(
    lines: Painter,
    fruit: Painter,
    modifier: Modifier = Modifier,
    lineColor: Color = MaterialTheme.colorScheme.onBackground,
    contentScale: ContentScale = ContentScale.Fit
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = lines,
            contentDescription = null,
            contentScale = contentScale,
            colorFilter = ColorFilter.tint(lineColor)
        )
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = fruit,
            contentDescription = null,
            contentScale = contentScale
        )
    }
}
