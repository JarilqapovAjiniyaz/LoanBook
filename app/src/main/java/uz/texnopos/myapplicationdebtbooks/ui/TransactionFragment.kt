package uz.texnopos.myapplicationdebtbooks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.myapplicationdebtbooks.AddandReadViewModel
import uz.texnopos.myapplicationdebtbooks.R
import uz.texnopos.myapplicationdebtbooks.ResourceState
import uz.texnopos.myapplicationdebtbooks.UserTransactionAdapter
import uz.texnopos.myapplicationdebtbooks.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment(R.layout.fragment_transaction) {
    private lateinit var binding: FragmentTransactionBinding
    private var adapter: UserTransactionAdapter = UserTransactionAdapter()
    private val viewModel: AddandReadViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransactionBinding.bind(view)
        binding.recyclerView.adapter = adapter

        viewModel.transactionLive.observe(viewLifecycleOwner, {
            when (it.status) {
                ResourceState.LOADING -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                ResourceState.SUCCESS -> {
                    adapter.models = it.data!!

                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}