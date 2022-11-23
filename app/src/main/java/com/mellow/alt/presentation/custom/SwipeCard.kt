package com.mellow.alt.presentation.custom

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun SwipeCard() {
    val translationXRatio: Float = 1 / 3f
    val translationYRatio: Float = 1 / 5f
    val rotationRatio: Float = 1 / 50f

    val density = LocalDensity.current

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var rotation by remember { mutableStateOf(0f) }

    val velocityTracker = androidx.compose.ui.input.pointer.util.VelocityTracker()

    Card(
        modifier = Modifier
            .offset((offsetX / density.density).dp, (offsetY / density.density).dp)
            .rotate(rotation)
            .fillMaxSize()
            .padding(24.dp, 32.dp, 24.dp, 32.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        velocityTracker.addPosition(change.previousUptimeMillis, change.position)

                        offsetX += dragAmount.x * translationXRatio
                        offsetY += dragAmount.y * translationYRatio
                        rotation += dragAmount.x * rotationRatio
                    },
                    onDragEnd = {
                        val velocity = velocityTracker.calculateVelocity()
                        velocityTracker.resetTracking()
                        if (velocity.x > 1000)
                            return@detectDragGestures

                        val currentTranslationX = offsetX
                        val currentTranslationY = offsetY
                        val currentRotation = rotation

                        val translationXAnimator =
                            ValueAnimator
                                .ofFloat(currentTranslationX, 0f)
                                .apply {
                                    addUpdateListener {
                                        offsetX = it.animatedValue as Float
                                    }
                                }

                        val translationYAnimator =
                            ValueAnimator
                                .ofFloat(currentTranslationY, 0f)
                                .apply {
                                    addUpdateListener {
                                        offsetY = it.animatedValue as Float
                                    }
                                }

                        val rotationAnimator = ValueAnimator
                            .ofFloat(currentRotation, 0f)
                            .apply {
                                addUpdateListener {
                                    rotation = it.animatedValue as Float
                                }
                            }

                        AnimatorSet().apply {
                            duration = 200L
                            playTogether(
                                translationXAnimator,
                                translationYAnimator,
                                rotationAnimator
                            )

                            start()
                        }
                    },
                    onDragCancel = {
                        offsetX = 0f
                        offsetY = 0f
                        rotation = 0f
                    },
                    onDragStart = {
                    }
                )
            },
        elevation = 10.dp,
        shape = RoundedCornerShape(24.dp)
    ) {

        Text(text = "SOSI")
    }
}