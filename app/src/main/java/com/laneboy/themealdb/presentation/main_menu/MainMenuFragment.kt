package com.laneboy.themealdb.presentation.main_menu

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.laneboy.themealdb.App
import com.laneboy.themealdb.R
import com.laneboy.themealdb.databinding.FragmentMainMenuBinding
import com.laneboy.themealdb.domain.models.MealCategory
import com.laneboy.themealdb.presentation.utils.AdFactory
import com.laneboy.themealdb.presentation.utils.ViewModelFactory
import com.laneboy.themealdb.presentation.main_menu.ad_list_adapter.AdAdapter
import com.laneboy.themealdb.presentation.main_menu.meal_list_adapter.MealAdapter
import java.lang.RuntimeException
import javax.inject.Inject

class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding: FragmentMainMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMainMenuBinding == null")

    private var tabId: Int = 0

    private val adItemAdapter by lazy {
        AdAdapter()
    }

    private val mealItemAdapter by lazy {
        MealAdapter()
    }

    private val mainMenuViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainMenuViewModel::class.java]
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adFactory: AdFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.navigationBarColor =
            requireActivity().getColor(R.color.grey_for_bottom_navigation)
        setUI()
        setSwipeToRefresh()
        observeViewModel()
    }

    private fun setUI() {
        setAdapters()
        setTabLayoutClickListener()
    }

    private fun setAdapters() {
        binding.rvAds.adapter = adItemAdapter
        binding.rvMeals.adapter = mealItemAdapter
        adItemAdapter.submitList(adFactory.createAds())
        val rvContext = binding.rvMeals.context
        binding.rvMeals.adapter = mealItemAdapter
        binding.rvMeals.addItemDecoration(
            DividerItemDecoration(rvContext, DividerItemDecoration.VERTICAL)
        )
    }

    private fun setTabLayoutClickListener() {
        binding.tlCategories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: Tab?) {
                tab?.let { tabId = it.position }
                mainMenuViewModel.getMeals(tab?.position)
            }

            override fun onTabUnselected(p0: Tab?) {}

            override fun onTabReselected(p0: Tab?) {}

        })
    }

    private fun observeViewModel() {
        mainMenuViewModel.mealItems.observe(viewLifecycleOwner) {
            it.ifLoading {
                binding.piLoading.visibility = VISIBLE
                binding.tvConnectionError.visibility = GONE
                binding.rvMeals.visibility = GONE
            }.ifSuccess { meals ->
                binding.piLoading.visibility = GONE
                binding.tvConnectionError.visibility = GONE
                binding.rvMeals.visibility = VISIBLE
                mealItemAdapter.submitList(meals)
            }.ifError {
                binding.piLoading.visibility = GONE
                binding.tvConnectionError.visibility = VISIBLE
            }
        }
        mainMenuViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categories.forEach { mealCategory ->
                addTab(mealCategory)
            }
        }
    }

    private fun addTab(mealCategory: MealCategory) {
        binding.tlCategories.addTab(
            binding.tlCategories.newTab().setText(mealCategory.categoryName)
        )
    }

    private fun setSwipeToRefresh() {
        val swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setColorSchemeColors(requireActivity().getColor(R.color.pink))
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            mainMenuViewModel.getMeals(mainMenuViewModel.getCurrentCategoryId())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainMenuFragment()
    }
}