package app.softxperttask.model.reposatories.api
import app.softxperttask.model.carsDataResponse.CarsDataResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/*
* create by mostafa anter
 */
interface ApiService {
    // get cars data
    @GET("api/v1/cars")
    fun getCarsDataApi(@Query("page") page: Int): Single<CarsDataResponse>

}