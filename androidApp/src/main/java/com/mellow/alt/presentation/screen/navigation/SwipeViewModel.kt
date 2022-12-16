package com.mellow.alt.presentation.screen.navigation

import androidx.lifecycle.ViewModel
import com.mellow.alt.domain.usecase.LoginSendCodeUseCase
import javax.inject.Inject

class SwipeViewModel @Inject constructor(private val loginSendCodeUseCase: LoginSendCodeUseCase) : ViewModel() {
}