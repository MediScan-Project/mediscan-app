package com.compfest.mediscanapp.ui.article

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.compfest.mediscanapp.LoadingState
import com.compfest.mediscanapp.api.response.DataArtikelItem
import com.compfest.mediscanapp.databinding.FragmentArticleBinding
import com.compfest.mediscanapp.paging.ModelFactory
import kotlin.collections.ArrayList


class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var factory: ModelFactory
    private val articleModel: ViewModelArticle by viewModels { factory}
    private lateinit var articles: ArrayList<DataArtikelItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view: View = binding.root
        factory = ModelFactory.getInstance(requireActivity())

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvArticle.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvArticle.addItemDecoration(itemDecoration)
        binding.rvArticle.setHasFixedSize(true)

        adapterCheck()
        storyResponse()
        return view
    }

    private fun adapterCheck() {
        val adapter = ArticleAdapter()
        binding.rvArticle.adapter = adapter.withLoadStateFooter(
            footer = LoadingState {
                adapter.retry()
            }
        )
        articleModel.pagingStory.observe(requireActivity()) {
            adapter.submitData(lifecycle, it)
        }
        articleModel.logModel()
    }

    private fun storyResponse(){
        articleModel.listArticle.observe(requireActivity()) {
            articles = it.dataArtikel as ArrayList<DataArtikelItem>
            Log.d("story", articles.toString())
        }
    }
//    private fun showLoading(state: Boolean) {
//        binding..visibility = if (state) View.VISIBLE else View.GONE
//    }
}

