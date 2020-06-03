package app.softxperttask.view.carsDataList

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.softxperttask.R
import app.softxperttask.databinding.FragmentCarsListBinding
import app.softxperttask.model.carsDataResponse.Data
import app.softxperttask.viewModel.CarsDataViewModel
import app.softxperttask.viewModel.CarsDataViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class CarsListFragment : Fragment() {
    private var mAdapter: CarsListAdapter? = null
    private var scrollListener: RecyclerViewScrollListener? = null
    private val modelList =
        ArrayList<Data>()

    // set binding
    private lateinit var binding: FragmentCarsListBinding

    // for view model
    private var pageNum = 1;
    val model: CarsDataViewModel by viewModels {
        CarsDataViewModelFactory(pageNum)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cars_list, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        binding.swipeRefreshRecyclerList.setOnRefreshListener { // Do your stuff on refresh
            binding.swipeRefreshRecyclerList!!.isRefreshing = true
            model.refreshData(1)
        }

        // get data
        binding.swipeRefreshRecyclerList!!.isRefreshing = true
        loadData()
    }

    private fun setAdapter() {
        mAdapter = CarsListAdapter(requireActivity(), modelList)
        binding.recyclerView.setHasFixedSize(true)

        // use a linear layout manager
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = mAdapter
        scrollListener = object : RecyclerViewScrollListener() {
            override fun onEndOfScrollReached(rv: RecyclerView) {
                binding.swipeRefreshRecyclerList.isRefreshing = true
                model.refreshData(pageNum)
                scrollListener!!.disableScrollListener()
            }
        }
        binding.recyclerView.addOnScrollListener(scrollListener!!)
        /*
             Note: The below two methods should be used wisely to handle the pagination enable and disable states based on the use case.
                     1. scrollListener.disableScrollListener(); - Should be called to disable the scroll state.
                     2. scrollListener.enableScrollListener(); - Should be called to enable the scroll state.
          */mAdapter!!.SetOnItemClickListener(object :
            CarsListAdapter.OnItemClickListener {
            override fun onItemClick(
                view: View?,
                position: Int,
                model: Data?
            ) {

                //handle item click events here
                Toast.makeText(activity, "is used" + model!!.isUsed, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadData() {
        model.getDataRepository()?.observe(requireActivity(), Observer {
            mAdapter?.addDataToExist(it.data)
            mAdapter?.notifyDataSetChanged()

            scrollListener!!.enableScrollListener()
            pageNum++
            binding.swipeRefreshRecyclerList.isRefreshing = false
        })
    }
}