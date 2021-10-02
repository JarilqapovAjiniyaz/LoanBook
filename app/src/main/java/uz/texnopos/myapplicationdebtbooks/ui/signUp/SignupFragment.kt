package uz.texnopos.myapplicationdebtbooks.ui.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.myapplicationdebtbooks.R
import uz.texnopos.myapplicationdebtbooks.databinding.FragmentSignupBinding

class SignupFragment() : Fragment(R.layout.fragment_signup) {
        private lateinit var binding : FragmentSignupBinding
        val viewModel:SignUpViewModel by viewModel()
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding = FragmentSignupBinding.bind(view)
            binding.signUpButton.setOnClickListener {
                if (binding.emailEditText.text.isNullOrEmpty() || binding.passwordEditText.text.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Email yamasa Paroldi kiritin'", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.signUp(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
                }
            }
            viewModel.signUp.observe(viewLifecycleOwner,{
                when(it){
                    "loading"->{
                        binding.loading.isVisible = true
                    }
                    "success"->{
                        binding.loading.isVisible = false
                      findNavController().navigate(R.id.action_signupFragment_to_mainFragment)
                    }
                    else->{
                        binding.loading.isVisible = false
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
}