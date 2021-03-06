package com.example.nazariy.geekapps.presentation.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.example.nazariy.geekapps.R
import com.example.nazariy.geekapps.domain.model.rss.Result
import com.example.nazariy.geekapps.presentation.view.ItunesItemAdapter
import com.example.nazariy.geekapps.presentation.viewmodel.ItunesViewModel
import kotlinx.android.synthetic.main.fragment_itunes_items.*

open class ItunesFragment : Fragment(), AdapterClickListener, ItunesItemAdapter.OnFavouriteAdded {

    protected lateinit var itunesViewModel: ItunesViewModel
    protected lateinit var itunesList: RecyclerView
    private lateinit var itunesItemAdapter: ItunesItemAdapter

    private var listener: OnFragmentAppearedListener? = null

    protected open var localFragmentTag: String = ItunesFragment::class.java.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentAppearedListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentAppearedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onResume() {
        super.onResume()
        listener?.changeBottomNavItem(localFragmentTag)
    }

    protected open fun initViewModel() {
        itunesViewModel = ViewModelProviders.of(this)
                .get(ItunesViewModel::class.java)
        itunesViewModel.error.observe(this, Observer { value ->
            value?.let { showMessage(value) }
        })

        itunesViewModel.itunesItems.observe(this, Observer { value ->
            value?.let { obtainResult(value) }
        })
    }

    protected open fun initUi(root: View) {
        itunesItemAdapter = ItunesItemAdapter(this, this)
        itunesList.adapter = itunesItemAdapter

        itunesList.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL,
                false)
    }

    private fun obtainResult(result: List<Result>) {
        noDataFound.visibility = View.GONE

        val controller: LayoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right)

        itunesList.layoutAnimation = controller
        itunesItemAdapter.update(result)
        itunesList.scheduleLayoutAnimation()
    }

    private fun showMessage(message: String) {
        noDataFound.text = message
        noDataFound.visibility = View.VISIBLE
    }

    override fun onClick(id: String?) {
        id?.let { DetailsFragment.newInstance(it) }?.let {
            activity?.supportFragmentManager?.beginTransaction()
                    ?.setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    ?.replace(R.id.itunes_container, it)
                    ?.addToBackStack(DetailsFragment::class.java.simpleName)
                    ?.commit()
        }
    }

    override fun onFavouriteItemChanged(result: Result) {
        itunesViewModel.saveFavourite(result)
    }

    interface OnFragmentAppearedListener {
        fun changeBottomNavItem(tag: String)
    }
}