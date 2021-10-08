package com.icodeu.bakeryapp.presentation.item

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.icodeu.bakeryapp.R
import com.icodeu.bakeryapp.databinding.FragmentItemBinding
import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.presentation.MainViewModel
import com.icodeu.bakeryapp.presentation.item.rv_adapters.SimilarRVAdapter
import com.icodeu.bakeryapp.utils.CommonUtils.shortSnackbar
import com.icodeu.bakeryapp.core.utils.Resource
import com.icodeu.bakeryapp.utils.collectWhenStarted
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ItemFragment : BottomSheetDialogFragment(), SimilarRVAdapter.SimilarItemInterface {

    private val TAG = ItemFragment::class.java.simpleName
    private val PARAM1 = "bread"

    private lateinit var binding: FragmentItemBinding
    private lateinit var similarAdapter: SimilarRVAdapter

    private val itemViewModel: ItemViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()

    private var bread: com.icodeu.bakeryapp.domain.model.Bread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.bread = it.getParcelable(PARAM1)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let {
                setupFullHeight(it)
                setupBehavior(it)
            }
        }
        return dialog

    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun setupBehavior(bottomSheet: View) {
        val behaviour = BottomSheetBehavior.from(bottomSheet)
        behaviour.state = BottomSheetBehavior.STATE_HIDDEN
        behaviour.state = BottomSheetBehavior.STATE_EXPANDED
        behaviour.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
                    binding.appBarLayout.visibility = View.VISIBLE
                }
                if (BottomSheetBehavior.STATE_DRAGGING == newState) {
                    binding.appBarLayout.visibility = View.INVISIBLE
                }

                if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    dismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })
    }

    private fun hideAppBar(view: View) {
        val params = view.layoutParams
        params.height = 0
        view.layoutParams = params
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAppBar(binding.appBarLayout)

        setupListeners()
        setupDetail()
        setupSimilar()
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            dismiss()
        }
        binding.toggleFavorite.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                binding.scrollView.shortSnackbar(getString(R.string.message_successfully_add_favorite))
            } else {
                binding.scrollView.shortSnackbar(getString(R.string.message_successfully_remove_favorite))
            }
        }
    }

    private fun setupDetail() {
        this.bread?.let { bread ->
            binding.bread = bread
            Glide.with(requireContext())
                .load(bread.validImage())
                .placeholder(R.drawable.ic_baseline_broken_image_32)
                .into(binding.ivBread)
        }
    }

    private fun setupSimilar() {
        similarAdapter = SimilarRVAdapter(this)

        itemViewModel.similar.collectWhenStarted(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.similarShimmerContainer.startShimmer()
                    binding.similarShimmerContainer.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    it.data?.let { similar -> similarAdapter.submitList(similar) }
                    binding.similarShimmerContainer.stopShimmer()
                    binding.similarShimmerContainer.visibility = View.GONE
                }
                is Resource.Error -> {
                    it.error?.let { message -> showError(message) }
                    it.data?.let { similar -> similarAdapter.submitList(similar) }
                    binding.similarShimmerContainer.stopShimmer()
                    binding.similarShimmerContainer.visibility = View.GONE
                }
            }
        }
        binding.apply {
            rvSimilar.hasFixedSize()
            rvSimilar.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            rvSimilar.adapter = similarAdapter
        }
    }

    override fun onResume() {
        binding.similarShimmerContainer.startShimmer()
        super.onResume()
    }

    override fun onStop() {
        binding.similarShimmerContainer.stopShimmer()
        super.onStop()
    }

    fun showLoading(isLoading: Boolean) {
        mainViewModel.showDialog(isLoading)
    }

    fun showError(errorMessage: String) {
        requireView().shortSnackbar(errorMessage)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: com.icodeu.bakeryapp.domain.model.Bread, param2: String? = "") =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAM1, param1)
                }
            }
    }

    override fun onItemSelected(position: Int, item: com.icodeu.bakeryapp.domain.model.Bread) {
        val fragment = ItemFragment.newInstance(item)
        fragment.show(childFragmentManager, "")
    }
}