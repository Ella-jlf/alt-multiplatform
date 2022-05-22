package com.mellow.alt.presentation.custom

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Insets
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import com.mellow.alt.databinding.ViewSwipeBinding
import kotlin.math.abs


class SwipeView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {
    private val binding = ViewSwipeBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private val baseAnimationDuration = 300L

    private var prevTouchY: Float = 0f
    private var prevTouchX: Float = 0f

    private var velocityTracker: VelocityTracker? = null

    private fun dragViewAlongWithFinger(dx: Float, dy: Float) {
        binding.vSwipe.rotation = dx / 50
        binding.vSwipe.translationY = dy / 5
        binding.vSwipe.translationX = dx / 3

        setImgAlpha(dx)

/*        Log.i(
            "DEBUGSOSI",
            "Log:\n DY: $dy, translation: ${dy / 3} \n DX: $dx, translation ${dx / 5} \n rotation: ${dx / 50}"
        )*/
    }

    private fun setImgAlpha(dx: Float) {

        if (dx > 0) {
            binding.vSwipeYesImage.alpha = dx / 500
            binding.vSwipeNopeImage.alpha = 0f
        } else {
            binding.vSwipeNopeImage.alpha = abs(dx / 500)
            binding.vSwipeYesImage.alpha = 0f
        }

    }

    private fun isViewSwiped(dx: Float): Boolean {
        if (abs(dx / 500 * 3) >= 1f) {
            tossViewOutOfScreen(dx)
            return true
        }
        return false
    }


    private fun getScreenWidth(): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    private fun animateToInitialPosition() {
        val currentTranslationX = binding.vSwipe.translationX
        val currentTranslationY = binding.vSwipe.translationY
        val currentRotation = binding.vSwipe.rotation

        val translationXAnimator = ValueAnimator.ofFloat(currentTranslationX, 0f).apply {
            addUpdateListener {
                binding.vSwipe.translationX = it.animatedValue as Float
                setImgAlpha(it.animatedValue as Float)
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

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (ev == null)
            return false

        return when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                velocityTracker?.recycle()
                velocityTracker = VelocityTracker.obtain()
                velocityTracker?.addMovement(ev)


                prevTouchX = ev.x
                prevTouchY = ev.y


                false
            }

            MotionEvent.ACTION_MOVE -> {
                velocityTracker?.addMovement(ev)

                true
            }

            else ->
                false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null)
            return false



        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                velocityTracker?.addMovement(event)

                val dX = event.x - prevTouchX
                val dY = event.y - prevTouchY


                dragViewAlongWithFinger(dX, dY)
                return true
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                velocityTracker?.computeCurrentVelocity(1000)

                if (isContinueMovement(velocityTracker?.xVelocity ?: 0f))
                    return true

                if (isViewSwiped(binding.vSwipe.translationX))
                    return true

                animateToInitialPosition()

                return true
            }
        }
        return false
    }

    private fun isContinueMovement(velocity: Float): Boolean {
        if (abs(velocity) > 2000) {
            tossViewOutOfScreen(velocity)
            return true
        }
        return false
    }

    private fun tossViewOutOfScreen(direction: Float) {
        val translationDx = if (direction > 0) {
            1000f
        } else {
            -1000f
        }

        ValueAnimator.ofFloat(binding.vSwipe.translationX, translationDx).apply {
            duration = baseAnimationDuration

            addUpdateListener {
                binding.vSwipe.translationX = it.animatedValue as Float
            }

            start()
        }
    }
}