package br.com.marbetramon.carstoreapp.model;

import java.util.List;

public class ListCars {
    public List<Cars> carsListagem;

    //teste abaixo, acima nao
    private Cars cars2;//teste
    private int carQuantity;

    //test
    public ListCars(Cars cars, int quantity) {
        cars2 = cars;
        carQuantity = quantity;
    }

    //test
    public Cars getCars() {
        return cars2;
    }

    public int getQuantity() {
        return carQuantity;
    }

    public void setQuantity(int quantity) {
        carQuantity = quantity;
    }


}
