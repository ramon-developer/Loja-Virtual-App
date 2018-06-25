package br.com.marbetramon.carstoreapp.activity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.marbetramon.carstoreapp.R;
import br.com.marbetramon.carstoreapp.adapter.ShoppingCartAdapter;
import br.com.marbetramon.carstoreapp.helper.CarStoreSharedPreference;
import br.com.marbetramon.carstoreapp.model.Cars;

public class ShoppingCartActivity extends AppCompatActivity {
    private static final String TAG = ShoppingCartActivity.class.getSimpleName();

    private RecyclerView shoppingCartRecyclerView;

    private double mSubTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checout);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Seu Carrinho");

        TextView subTotal = findViewById( R.id.sub_total );

        shoppingCartRecyclerView = (RecyclerView)findViewById(R.id.checkout_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingCartActivity.this);
        shoppingCartRecyclerView.setLayoutManager(linearLayoutManager);
        shoppingCartRecyclerView.setHasFixedSize(true);

        CarStoreSharedPreference mShared = new CarStoreSharedPreference(ShoppingCartActivity.this);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Cars[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), Cars[].class);

        if (addCartProducts != null) { //test
            List<Cars> productList = convertObjectArrayToListObject( addCartProducts );

            ShoppingCartAdapter cartAdapter = new ShoppingCartAdapter( ShoppingCartActivity.this, productList );
            shoppingCartRecyclerView.setAdapter( cartAdapter );

            mSubTotal = getTotalPrice( productList );
            subTotal.setText( "Subtotal: " + String.valueOf( mSubTotal ) + " $" );
        } //test

//
//        Button shoppingButton = (Button)findViewById(R.id.shopping);
//        assert shoppingButton != null;
//        shoppingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shoppingIntent = new Intent(ShoppingCartActivity.this, MainActivity.class);
//                startActivity(shoppingIntent);
//            }
//        });
//
//        Button checkButton = (Button)findViewById(R.id.checkout);
//        assert checkButton != null;
//        checkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent paymentIntent = new Intent(ShoppingCartActivity.this, MainActivity.class);
//                paymentIntent.putExtra("TOTAL_PRICE", mSubTotal);
//                startActivity(paymentIntent);
//            }
//        });
    }

    private List<Cars> convertObjectArrayToListObject(Cars[] allProducts){
        List<Cars> mProduct = new ArrayList<Cars>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private int returnQuantityByProductName(String productName, List<Cars> mProducts){
        int quantityCount = 0;
        for(int i = 0; i < mProducts.size(); i++){
            Cars pObject = mProducts.get(i);
            if(pObject.getNome().trim().equals(productName.trim())){
                quantityCount++;
            }
        }
        return quantityCount;
    }

    private double getTotalPrice(List<Cars> mProducts){
        double totalCost = 0;
        for(int i = 0; i < mProducts.size(); i++){
            Cars pObject = mProducts.get(i);
            totalCost = totalCost + pObject.getPreco();
        }
        return totalCost;
    }
}
