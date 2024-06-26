package com.onlineshop.online_shop

import androidx.lifecycle.ViewModel
import com.onlineshop.onlineshopkmmlibrary.useCases.LoginUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun login(eMail: String, password: String) {
        if (eMail.isNotBlank() && password.isNotBlank()) {
            loginUseCase.execute(eMail, password)
        }
    }
}