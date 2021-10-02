package uz.texnopos.myapplicationdebtbooks.ui.signIn


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.myapplicationdebtbooks.R
import uz.texnopos.myapplicationdebtbooks.databinding.FragmentSignInBinding

class SignInFragment() : Fragment(R.layout.fragment_sign_in) {

//    private lateinit var binding: FragmentSignInBinding
//
//    private val viewModel: SignInViewModel by viewModel()
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentSignInBinding.bind(view)
//
//        val firebaseAuth = FirebaseAuth.getInstance()
//        authCheckUser(firebaseAuth)
//        binding.signInButton.setOnClickListener {
//            if (binding.emailEditText.text.isNullOrEmpty() || binding.passwordEditText.text.isNullOrEmpty()) {
//                Toast.makeText(requireContext(), "Please fill all the fields ", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                viewModel.signIn(
//                    binding.emailEditText.text.toString(),
//                    binding.passwordEditText.text.toString()
//                )
//            }
//        }
//
//        binding.noAccountTextView.setOnClickListener {
//            findNavController().navigate(R.id.action_signInFragment_to_signupFragment)
//        }
//    }
//
//    fun authCheckUser(firebaseAuth: FirebaseAuth) {
//        if (firebaseAuth.currentUser != null) {
//            findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
//        } else {
//            Toast.makeText(requireContext(), "else", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun setUpObservers(){
//        viewModel.signIn.observe(viewLifecycleOwner, {
//            when (it) {
//                "loading" -> {
//                    binding.loading.isVisible = true
//                }
//                "success" -> {
//                    binding.loading.isVisible = false
//                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
//                }
//                else -> {
//                    binding.loading.isVisible = false
//                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//    }
}