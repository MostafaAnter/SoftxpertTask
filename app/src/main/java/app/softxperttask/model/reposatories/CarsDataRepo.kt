package app.softxperttask.model.reposatories

import androidx.lifecycle.MutableLiveData
import app.softxperttask.model.carsDataResponse.CarsDataResponse
import app.softxperttask.model.reposatories.api.ApiService
import app.softxperttask.model.reposatories.api.RestClient

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

// created by mostafa Anter
class CarsDataRepo(val page: Int) {
    lateinit var apiService: ApiService
    lateinit var compositeDisposable: CompositeDisposable

    public fun getCarsData(): MutableLiveData<CarsDataResponse> {
        val serverData: MutableLiveData<CarsDataResponse> = MutableLiveData()
        apiService = RestClient.getInterfaceInstance("http://demo1286023.mockable.io/")!!
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(apiService.getCarsDataApi(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    respone -> serverData.value = respone
                })

        return serverData

    }
}