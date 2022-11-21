package com.mellow.alt.di

import com.mellow.alt.domain.repository.LoginRepository
import com.mellow.alt.domain.usecase.LoginSendCodeUseCase
import com.mellow.alt.domain.usecase.RegisterSendCodeUseCase
import com.mellow.alt.domain.usecase.RegisterSendUserInfoUseCase
import com.mellow.alt.domain.usecase.SendPhoneUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideLoginSendCodeUseCase(loginRepository: LoginRepository): LoginSendCodeUseCase {
        return LoginSendCodeUseCase(loginRepository)
    }

    @Provides
    fun provideRegisterSendCodeUseCase(loginRepository: LoginRepository): RegisterSendCodeUseCase {
        return RegisterSendCodeUseCase(loginRepository)
    }

    @Provides
    fun provideRegisterSendUserInfoUseCase(loginRepository: LoginRepository): RegisterSendUserInfoUseCase {
        return RegisterSendUserInfoUseCase(loginRepository)
    }

    @Provides
    fun provideSendPhoneUseCase(loginRepository: LoginRepository): SendPhoneUseCase {
        return SendPhoneUseCase(loginRepository)
    }
}