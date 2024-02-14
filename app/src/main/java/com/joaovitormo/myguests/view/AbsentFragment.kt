package com.joaovitormo.myguests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaovitormo.myguests.constants.DataBaseConstants
import com.joaovitormo.myguests.databinding.FragmentAbsentBinding
import com.joaovitormo.myguests.view.adapter.GuestsAdapter
import com.joaovitormo.myguests.view.listener.OnGuestListener
import com.joaovitormo.myguests.view.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        // Atribui um layout que diz como a RecyclerView se comporta
        binding.recyclerAbsents.layoutManager = LinearLayoutManager(context)

        // Define um adapater - Faz a ligação da RecyclerView com a listagem de itens
        binding.recyclerAbsents.adapter = adapter

        // Cria comportamento quando item for clicado
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                // Intenção
                val intent = Intent(context, GuestFormActivity::class.java)

                // "Pacote" de valores que serão passados na navegação
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)

                // Atribui o pacote a Intent
                intent.putExtras(bundle)

                // Inicializa Activity com dados
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAbsent()
            }
        }

        // Cria os observadores
        observe()

        adapter.attachListener(listener)
        return binding.root
    }

    /**
     * Ciclo de vida - onResume
     */
    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Cria os observadores
     */
    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updatedGuests(it)
        }
    }
}