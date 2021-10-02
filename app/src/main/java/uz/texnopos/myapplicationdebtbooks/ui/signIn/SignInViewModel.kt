package uz.texnopos.myapplicationdebtbooks.ui.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.texnopos.myapplicationdebtbooks.data.AuthHelper

class SignInViewModel(private val authHelper: AuthHelper) : ViewModel() {
    var signIn: MutableLiveData<String> = MutableLiveData()
    fun signIn(email: String, password: String) {
        signIn.value = "loading"
        authHelper.signIn(email, password,
           onSuccess = {
                signIn.value = "success"
            },
            onFailure = {
                signIn.value = it
            }
        )
    }
}