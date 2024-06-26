package com.onlineshop.online_shop

import androidx.lifecycle.ViewModel
import com.onlineshop.onlineshopkmmlibrary.useCases.SignUpUseCase

class SignupViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    fun signup(firstName: String, lastName: String, eMail: String, password: String, role: String) {
        signUpUseCase.execute(firstName, lastName, eMail, password, role)
    }
}