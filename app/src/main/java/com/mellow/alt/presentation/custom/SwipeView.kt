package com.mellow.alt.presentation.custom

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.FrameLayout
import com.mellow.alt.databinding.ViewSwipeBinding
import java.util.concurrent.atomic.AtomicBoolean

class SwipeView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {
    private val binding = ViewSwipeBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private val baseAnimationDuration = 400L

    private var prevTouchY: Float = 0f
    private var prevTouchX: Float = 0f

    private var pointerLock: AtomicBoolean = AtomicBoolean(true)

    private fun dragViewAlongWithFinger(dx: Float, dy: Float) {
        binding.vSwipe.rotation = dx / 50
        binding.vSwipe.translationY = dy / 5
        binding.vSwipe.translationX = dx / 3
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (ev == null)
            return false

        return when (ev.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> {

                prevTouchX = ev.x
                prevTouchY = ev.y


                false
            }

            MotionEvent.ACTION_MOVE -> {
                true
            }

            else ->
                false
        }
    }

    private fun animateToInitialPosition() {
        val currentTranslationX = binding.vSwipe.translationX
        val currentTranslationY = binding.vSwipe.translationY
        val currentRotation = binding.vSwipe.rotation

        val translationXAnimator = ValueAnimator.ofFloat(currentTranslationX, 0f).apply {
            addUpdateListener {
                binding.vSwipe.translationX = it.animatedValue as Float
            }
        }

        val translationYAnimator = ValueAnimator.ofFloat(currentTranslationY, 0f).apply {
            addUpdateListener {
                binding.vSwipe.translationY = it.animatedValue as Float
            }
        }

        val rotationAnimator = ValueAnimator.ofFloat(currentRotation, 0f).apply {
            addUpdateListener {
                binding.vSwipe.rotation = it.animatedValue as Float
            }
        }

        AnimatorSet().apply {
            duration = baseAnimationDuration
            playTogether(translationXAnimator, translationYAnimator, rotationAnimator)

            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    binding.vSwipe.isClickable = false
                    binding.vSwipe.isFocusable = false
                }

                override fun onAnimationEnd(p0: Animator?) {
                    binding.vSwipe.isFocusable = true
                    binding.vSwipe.isClickable = true
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationRepeat(p0: Animator?) {
                }

            })

            start()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null)
            return false



        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                val dX = event.x - prevTouchX
                val dY = event.y - prevTouchY


                dragViewAlongWithFinger(dX, dY)
                return true
            }

            MotionEvent.ACTION_POINTER_UP -> {
                pointerLock.set(true)
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {

                animateToInitialPosition()
                return true
            }
        }
        return false
    }
}