package com.alejandro.pruebas.login.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("ok") val okLogin : Boolean)