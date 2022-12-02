package com.alejandro.monkeyfilmapp.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.alejandro.monkeyfilmapp.login.ui.LoginViewModel
import com.alejandro.monkeyfilmapp.ui.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterModelView : LoginViewModel(){
    private val _user = MutableLiveData<String>()
    val user = _user

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword = _confirmPassword
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    private val _accion = MutableLiveData<Boolean>()
    val accion = _accion
    fun changeAccion(accion : Boolean){
        _accion.value = accion
    }

    private val _drama = MutableLiveData<Boolean>()
    val drama = _drama
    fun changeDrama(drama : Boolean){
        _drama.value = drama
    }

    private val _crimen = MutableLiveData<Boolean>()
    val crimen = _crimen
    fun changeCrimen(crimen : Boolean){
        _crimen.value = crimen
    }

    private val _aventura = MutableLiveData<Boolean>()
    val aventura = _aventura
    fun changeAventura(aventura : Boolean){
        _aventura.value = aventura
    }

    private val _fantasia = MutableLiveData<Boolean>()
    val fantasia = _fantasia
    fun changeFantasia(fantasia : Boolean){
        _fantasia.value = fantasia
    }

    private val _sifi = MutableLiveData<Boolean>()
    val sifi = _sifi
    fun changeSifi(sifi : Boolean){
        _sifi.value = sifi
    }

    private fun validPassword(password : String) : Boolean = password.length > 6
    private fun validUser(user:String) : Boolean = user.length > 6
    private fun validateSecondPassword(password : String, confirmPassword : String) : Boolean = (password == confirmPassword)

     fun validRegister(user: String, password: String, confirmPassword: String, email: String){
        _email.value = email
        _user.value = user
        _password.value = password
        _confirmPassword.value = confirmPassword
        _isButtonLoginEnable.value =  validEmail(email) && validPassword(password) && validUser(user) && validateSecondPassword(password, confirmPassword)
    }

    fun registerButtonPress(navController : NavHostController){
        viewModelScope.launch {
            _isLoading.value = true
            delay(4000)
            _isLoading.value = false
            navController.navigate(Routes.Home.route)
        }
    }
}