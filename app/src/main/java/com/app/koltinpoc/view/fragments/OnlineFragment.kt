package com.app.koltinpoc.view.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.koltinpoc.R
import com.app.koltinpoc.databinding.FragmentOnlineBinding
import com.app.koltinpoc.utils.DataHandler
import com.app.koltinpoc.utils.LogData
import com.app.koltinpoc.view.adapter.HorizontalAdapter
import com.app.koltinpoc.view.adapter.VerticalAdapter
import com.app.koltinpoc.viewModel.OnlineViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnlineFragment : Fragment(R.layout.fragment_online) {

    private lateinit var binding: FragmentOnlineBinding

    @Inject
    lateinit var horizontalAdapter: HorizontalAdapter

    @Inject
    lateinit var verticalAdapter: VerticalAdapter

    val viewModel: OnlineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnlineBinding.bind(view)
        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        init()

        val state = arguments?.getBoolean("delete_state", false)

        viewModel.animeTop.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    horizontalAdapter.differ.submitList(dataHandler.data)
                    binding.swipeRefresh.isRefreshing = false
                }

                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                }

                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    LogData("onViewCreated: LOADING..")

                }
            }

        }

        viewModel.animeSeasonsNowTop.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    verticalAdapter.differ.submitList(dataHandler.data)
                    binding.swipeRefresh.isRefreshing = false
                }

                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                }

                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    LogData("onViewCreated: LOADING..")

                }
            }

        }

        viewModel.animeSearched.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val bundle = Bundle().apply {
                        putParcelable("article_data", dataHandler.data)
                    }
                    val navOptions = NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_right)
                        .setExitAnim(R.anim.slide_out_left)
                        .setPopEnterAnim(R.anim.slide_in_left)
                        .setPopExitAnim(R.anim.slide_out_right)
                        .build()
                    findNavController().navigate(
                        R.id.action_onlineFragment_to_articleDetailsFragment,
                        bundle,
                        navOptions
                    )
                    binding.swipeRefresh.isRefreshing = false
                }

                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                    val toast = Toast.makeText(requireContext(), "No se encontro el anime", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                }

                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    LogData("onViewCreated: LOADING..")

                }
            }

        }



        if (state!!.not()) {
            viewModel.getAnimeTop()
            viewModel.getAnimeSeasonsNow()
        } else {
            viewModel.getAllLocalAnimeInfo()
            viewModel.getAllLocalAnimeSeasonNowInfo()
        }
    }

    private fun init() {

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAnimeTop()
        }

        verticalAdapter.onAnimeInfoClicked {
            val bundle = Bundle().apply {
                putParcelable("article_data", it)
            }

            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()
            findNavController().navigate(
                R.id.action_onlineFragment_to_articleDetailsFragment,
                bundle,
                navOptions
            )
        }

        binding.recyclerView.apply {
            adapter = horizontalAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.recyclerviewAnimeNewestSeasons.apply {
            adapter = verticalAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        val searchView = binding.searchView
        searchView.setQuery("Jujutsu Kaisen 2nd Season",false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                manageQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun manageQuery(query: String?) {
        if (query.isNullOrEmpty()) {
            val toast = Toast.makeText(requireContext(), "Escribe un anime", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
            toast.show()

        } else {
            viewModel.searchAnimeByTitle(query)
        }
    }
}