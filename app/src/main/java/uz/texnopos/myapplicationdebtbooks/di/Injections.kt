package uz.texnopos.myapplicationdebtbooks.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.texnopos.myapplicationdebtbooks.*
import uz.texnopos.myapplicationdebtbooks.data.AuthHelper
import uz.texnopos.myapplicationdebtbooks.ui.MainFragment
import uz.texnopos.myapplicationdebtbooks.ui.signIn.SignInViewModel
import uz.texnopos.myapplicationdebtbooks.ui.signUp.SignUpViewModel

val dataModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }

}
val helperModule = module {
    single { AuthHelper(get()) }
    single { AddandReadHelper(get()) }
    single { ContactTransaction(get()) }
    single { HistoryTransaction(get()) }
    single { AddandReadHelper(get()) }

}
val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { AddandReadViewModel(get(), get(), get()) }
}