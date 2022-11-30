package com.alejandro.monkeyfilmapp.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.login.domin.LoginUseCase
import com.alejandro.monkeyfilmapp.ui.screens.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class LoginViewModel : ViewModel() {
    private val loginUseCase = LoginUseCase()

    protected val _email = MutableLiveData<String>()

    val email  = _email

    protected val _password = MutableLiveData<String>()

    val password : LiveData<String> = _password

    protected val _isButtonLoginEnable = MutableLiveData<Boolean>()

    val isButtonLoginEnable : LiveData<Boolean> = _isButtonLoginEnable

    protected val _isLoading = MutableLiveData<Boolean>()

    val isLoading : LiveData<Boolean> = _isLoading

    private val _forgottenEmail = MutableLiveData<String>()
    val forgottenEmail = _forgottenEmail

    fun onLoginChanged(email : String, password : String){
        _email.value = email
        _password.value = password
        _isButtonLoginEnable.value = validEmail(email) && validPassword(password)
    }

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog = _showDialog

    fun passwordForgotten(email : String){
        _forgottenEmail.value = email

    }

    fun hideDialog(){
        _showDialog.value = false
    }


    fun validEmail(email : String) : Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validPassword(password: String) : Boolean = password.length > 5

    fun onButtonLoginPress(navController : NavHostController){
        viewModelScope.launch {
            _isLoading.value = true
            delay(4000)
            val result = loginUseCase()
            if(result){
                Log.i("DAM", "Se ha completado el login")
                navController.navigate(Routes.Home.route)
            }
            _isLoading.value = false

        }
    }
}