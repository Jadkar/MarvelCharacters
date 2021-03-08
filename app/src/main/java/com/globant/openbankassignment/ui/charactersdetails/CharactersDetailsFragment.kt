package com.globant.openbankassignment.ui.charactersdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.openbankassignment.R
import com.globant.openbankassignment.ui.base.BaseFragment
import com.globant.openbankassignment.ui.characterslist.CharactersListActivity
import com.globant.openbankassignment.utils.InternetUtil
import com.openbank.domain.model.CharacterDetailsModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_characters_details.view.*
import javax.inject.Inject


class CharactersDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var characterDetailTypeAdapter: CharacterDetailTypeAdapter
    private lateinit var viewModel: CharactersDetailsViewModel
    private var characterId: Long = 0
    private lateinit var mBinding: ViewDataBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.fragment_characters_details,
            container, false
        )
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(CharactersDetailsViewModel::class.java)
        characterId = arguments?.getLong(CharactersListActivity.ARG_CHARACTER_ID)!!
        (activity as CharactersListActivity).supportActionBar?.title =
            arguments?.getString(CharactersListActivity.ARG_CHARACTER_NAME)
                ?: getString(R.string.charactersList_fragment_label)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getCharactersDetailsList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeToViewModel()
    }

    private fun initRecyclerView() {
        characterDetailTypeAdapter = CharacterDetailTypeAdapter(requireContext())
        mBinding.root.rvCharactersDetailsList.layoutManager = LinearLayoutManager(context)
        mBinding.root.rvCharactersDetailsList.adapter = characterDetailTypeAdapter
        characterDetailTypeAdapter.notifyDataSetChanged()
    }

    private fun getCharactersDetailsList() {
        if (InternetUtil.isInternetConnected()) {
            showLoadingIndicator(true)
            viewModel.getCharactersDetails(characterId)
        } else {
            showAlertMessage(
                getString(R.string.lbl_error_msg),
                getString(R.string.lbl_msg_no_internet_connection)
            )

            InternetUtil.observe(viewLifecycleOwner, Observer { status ->
                if (status != null && status) {
                    showLoadingIndicator(true)
                    viewModel.getCharactersDetails(characterId)
                }
            })
        }
    }

    private fun subscribeToViewModel() {
        viewModel.characterDetails.observe(viewLifecycleOwner, Observer {
            showLoadingIndicator(false)
            if (it != null) handleViewState(it)
            else showAlertMessage("", getString(R.string.lbl_no_data))
        })
        viewModel.getCharacterDetailsFailure.observe(viewLifecycleOwner, Observer<String> {
            showLoadingIndicator(false)
            if (!InternetUtil.isInternetConnected()) {
                showAlertMessage(
                    getString(R.string.lbl_internet_title),
                    getString(R.string.lbl_msg_no_internet_connection)
                )
            } else {
                showAlertMessage(getString(R.string.lbl_error_msg), it)
            }
        })
    }

    private fun handleViewState(characterDetailsList: List<CharacterDetailsModel>) {
        characterDetailTypeAdapter.setDetailsList(characterDetailsList)
    }

    private fun showLoadingIndicator(loading: Boolean) = if (loading) {
        mBinding.root.pbLoading.visibility = View.VISIBLE
    } else {
        mBinding.root.pbLoading.visibility = View.GONE
    }


}