package com.mellow.alt.presentation.compose

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.animation.addListener
import coil.compose.rememberAsyncImagePainter
import com.mellow.alt.presentation.compose.theme.AltAppTheme
import com.mellow.alt.presentation.compose.theme.LocalAltAppColors
import com.mellow.alt.presentation.compose.theme.LocalAltAppShapes
import com.mellow.alt.presentation.compose.theme.LocalAltAppTypography
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel
import com.mellow.alt.utils.SwipeCardNum

@Composable
fun SwipeCard(
    viewModel: SwipeViewModel,
    cardNum: SwipeCardNum
) {
    val toggle by viewModel.toggle.observeAsState()
    val displayAccount by when (cardNum) {
        SwipeCardNum.FIRST -> {
            viewModel.displayProfileFirst.observeAsState()
        }
        SwipeCardNum.SECOND -> {
            viewModel.displayProfileSecond.observeAsState()
        }
    }

    val translationXRatio: Float = 1 / 3f
    val translationYRatio: Float = 1 / 5f
    val rotationRatio: Float = 1 / 50f

    val density = LocalDensity.current

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var rotation by remember { mutableStateOf(0f) }

    val velocityTracker = androidx.compose.ui.input.pointer.util.VelocityTracker()

    var mainImage by remember { mutableStateOf(displayAccount?.imageList?.first()) }

    Card(
        modifier = Modifier
            .zIndex(if (cardNum == toggle!!) 2f else 1f)
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
                        if (kotlin.math.abs(velocity.x) > 1000) {
                            val currentTranslationX = offsetX
                            if (velocity.x > 0) {
                                ValueAnimator
                                    .ofFloat(currentTranslationX, screenWidth.toPx())
                                    .apply {
                                        addUpdateListener {
                                            offsetX = it.animatedValue as Float
                                        }
                                        addListener(onEnd = {
                                            viewModel.onSwipeRight(cardNum)
                                            offsetX = 0f
                                            offsetY = 0f
                                            rotation = 0f
                                        })
                                    }

                                    .start()
                            } else {
                                ValueAnimator
                                    .ofFloat(currentTranslationX, -screenWidth.toPx())
                                    .apply {
                                        addUpdateListener {
                                            offsetX = it.animatedValue as Float
                                        }
                                        addListener(onEnd = {
                                            viewModel.onSwipeLeft(cardNum)

                                            offsetX = 0f
                                            offsetY = 0f
                                            rotation = 0f
                                        })
                                    }
                                    .start()
                            }
                            return@detectDragGestures
                        }


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
        shape = AltAppTheme.shapes.primaryShape,
        backgroundColor = AltAppTheme.colors.primaryBackground

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = displayAccount?.name ?: "Empty",
                modifier = Modifier
                    .weight(1.5f),
                style = AltAppTheme.typography.primaryTypography
            )
            Image(
                painter = rememberAsyncImagePainter(mainImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f),
                contentScale = ContentScale.Fit
            )
            LazyRow(
                modifier = Modifier
                    .padding(24.dp, 16.dp)
                    .requiredHeight(76.dp)
                    .fillMaxWidth()
                    .clip(AltAppTheme.shapes.primarySmallShape)
                    .border(
                        1.dp,
                        AltAppTheme.colors.secondaryBackground,
                        shape = AltAppTheme.shapes.primarySmallShape
                    )
                    .padding(6.dp, 0.dp)
                    .weight(1.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(displayAccount?.imageList ?: listOf()) { item ->
                    Image(
                        painter = rememberAsyncImagePainter(item),
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(AltAppTheme.shapes.primarySmallShape)
                            .border(
                                1.dp,
                                AltAppTheme.colors.secondaryBackground,
                                AltAppTheme.shapes.primarySmallShape
                            )
                            .clickable {
                                if (mainImage != item) {
                                    mainImage = item
                                }
                            },
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }
}