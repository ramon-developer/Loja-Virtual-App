package br.com.marbetramon.carstoreapp.service;

import java.util.List;

import br.com.marbetramon.carstoreapp.model.Cars;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CarService {

    public static final String URL_BASE = "http://desafiobrq.herokuapp.com/v1/";

//    @GET("carro")
//    Call<List<Car>> listCar();

    //test
    @GET("carro")
    Call<List<Cars>> listCars();
}


