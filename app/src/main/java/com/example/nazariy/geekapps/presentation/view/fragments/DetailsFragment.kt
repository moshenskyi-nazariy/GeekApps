package com.example.nazariy.geekapps.presentation.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nazariy.geekapps.R
import com.example.nazariy.geekapps.databinding.FragmentDetailsBinding
import com.example.nazariy.geekapps.presentation.viewmodel.ItunesViewModel

class DetailsFragment : Fragment() {
    private lateinit var itunesViewModel: ItunesViewModel
    private lateinit var id: String
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString(ARG_ID) ?: savedInstanceState?.getString(ARG_ID) ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_details, container, false)

        initViewModel()
        itunesViewModel.loadDetails(id)

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARG_ID, id)
    }

    private fun initViewModel() {
        itunesViewModel = ViewModelProviders.of(this)
                .get(ItunesViewModel::class.java)
        itunesViewModel.error.observe(this, Observer { value ->
            value?.let { showMessage(value) }
        })

        itunesViewModel.details.observe(this, Observer { value ->
            value?.let { binding.model = value[0] }
        })
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ARG_ID = "id"

        fun newInstance(id: String): DetailsFragment {
            val args = Bundle()
            args.putSerializable(ARG_ID, id)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
