package uz.texnopos.myapplicationdebtbooks.data

import com.google.firebase.auth.FirebaseAuth

class AuthHelper(private val auth: FirebaseAuth) {
    fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.message)
            }
    }
    fun signUp(email: String,password: String,onSucces:()->Unit,onFailure:(msg:String?)->Unit){
    auth.createUserWithEmailAndPassword(email,password)
        .addOnSuccessListener{
        onSucces.invoke()
        }
        .addOnFailureListener{
        onFailure.invoke(it.message)
        }
    }
}