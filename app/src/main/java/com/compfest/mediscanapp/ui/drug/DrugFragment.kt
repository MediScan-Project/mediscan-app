package com.compfest.mediscanapp.ui.drug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.compfest.mediscanapp.LoadingState
import com.compfest.mediscanapp.databinding.FragmentDrugBinding
import com.compfest.mediscanapp.paging.ModelFactory
import com.compfest.mediscanapp.paging.SearchPagingModel

class DrugFragment : Fragment() {
    private var _binding: FragmentDrugBinding? = null
    private val binding get() = _binding!!
    private lateinit var factory: ModelFactory
    private val pagingModel: SearchPagingModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDrugBinding.inflate(inflater, container, false)
        val view: View = binding.root
        factory = ModelFactory.getInstance(requireActivity())
        val adapter = SearchDrugAdapter()
        searchDrug()
        binding.apply {
            val layoutManager = LinearLayoutManager(requireActivity())
            binding.rvDrug.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
            binding.rvDrug.addItemDecoration(itemDecoration)
            rvDrug.setHasFixedSize(true)
            rvDrug.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoadingState { adapter.retry() },
                footer = LoadingState { adapter.retry() },
            )
        }

        pagingModel.pagingDrug.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        return view
    }


    private fun searchDrug() {
//        val searchDrug =
        binding.searchDrug.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.rvDrug.scrollToPosition(0)
                    pagingModel.searchDrug(query)
                    binding.searchDrug.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}