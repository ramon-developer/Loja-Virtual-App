package br.com.marbetramon.carstoreapp.model;

public class ShoppingCartEntry {

    private Cars cars;
    private int carQuantity;

    public ShoppingCartEntry(Cars car, int quantity) {
        cars = car;
        carQuantity = quantity;
    }

    public Cars getCars() {
        return cars;
    }

    public int getQuantity() {
        return carQuantity;
    }

    public void setQuantity(int quantity) {
        carQuantity = quantity;
    }

}
