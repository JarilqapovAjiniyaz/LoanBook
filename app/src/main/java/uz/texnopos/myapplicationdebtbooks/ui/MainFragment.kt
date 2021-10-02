package uz.texnopos.myapplicationdebtbooks.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.myapplicationdebtbooks.*

import uz.texnopos.myapplicationdebtbooks.databinding.FloatingActionButtonBinding
import uz.texnopos.myapplicationdebtbooks.databinding.FragmentMainBinding
import uz.texnopos.myapplicationdebtbooks.databinding.ItemBinding


class MainFragment() : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: AddandReadViewModel by viewModel()
    private val adapterUser: AdapterUser = AdapterUser(this)
    private lateinit var b: ItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        viewModel.getContact()
        binding.floatingActionButton.setOnClickListener {
            CustomDialog(requireContext(), plus = { name, amount, coment, date ->
                viewModel.addandReadviewModelPlus(name, amount, coment, date)
                b.tvBalance.setTextColor(requireContext().getColor(R.color.purple_700))
            }, minus = { name, amount, coment, date ->
                viewModel.addandReadviewModelMinus(name, amount, coment, date)
            })
                .show()
        }

        //binding.recyclerView.adapter = adapterUser
        viewModel.transaction.observe(viewLifecycleOwner, {
            when (it) {
                "Loading" -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                "Success" -> {
                    Toast.makeText(requireContext(), "isledi", Toast.LENGTH_SHORT).show()
                    viewModel.getContact()
                }
                it -> {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.contactsLive.observe(viewLifecycleOwner, {
            when (it.status) {
                ResourceState.LOADING -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                ResourceState.SUCCESS -> {
                    adapterUser.models = it.data!!

                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /*fun onOptionButtonClick(view: View) {
        var optionsMenu = PopupMenu(requireContext(), view.moreVert)
        val menuInflater = optionsMenu.menuInflater
        menuInflater.inflate(R.menu.menu_item_options, optionsMenu.menu)
        optionsMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.itemOne -> {

                }
                R.id.itemTwo -> {

                }
                R.id.itemThree -> {
                    viewModel.getTransaction(view.tvName.text.toString())
                    findNavController().navigate(R.id.action_mainFragment_to_transactionFragment)

                }
                R.id.itemFour -> {

                }
                R.id.itemFive -> {

                }
                R.id.itemSix -> {

                }
            }
            return@setOnMenuItemClickListener true

        }
        optionsMenu.show()
    }*/
}