package uz.texnopos.myapplicationdebtbooks.ui.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.texnopos.myapplicationdebtbooks.data.AuthHelper

class SignUpViewModel(private val authHelper: AuthHelper) : ViewModel() {
    var signUp: MutableLiveData<String> = MutableLiveData()
    fun signUp(email: String, password: String) {
        signUp.value = "loading"
        authHelper.signUp(email, password, onSucces = {
            signUp.value = "success"
        }, onFailure = {
            signUp.value = it
        })
    }
}