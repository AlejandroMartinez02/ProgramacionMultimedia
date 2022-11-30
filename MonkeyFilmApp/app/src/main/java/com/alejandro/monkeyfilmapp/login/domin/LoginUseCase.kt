package com.alejandro.monkeyfilmapp.login.domin

import com.alejandro.monkeyfilmapp.login.data.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(): Boolean {
        return repository.doLogin()
    }
}