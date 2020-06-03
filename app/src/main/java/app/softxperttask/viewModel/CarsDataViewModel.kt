package app.softxperttask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.softxperttask.model.carsDataResponse.CarsDataResponse
import app.softxperttask.model.reposatories.CarsDataRepo


class CarsDataViewModel(val page: Int): ViewModel() {

    private val reloadTrigger = MutableLiveData<Int>()

    private val mutableLiveData: LiveData<CarsDataResponse> = Transformations.switchMap(reloadTrigger){
        CarsDataRepo(page = it).getCarsData()
    }

    init {
        refreshData(page)
    }

    fun getDataRepository(): LiveData<CarsDataResponse>? {
        return mutableLiveData
    }

    fun refreshData(pageNum: Int) {
        reloadTrigger.value = pageNum
    }
}