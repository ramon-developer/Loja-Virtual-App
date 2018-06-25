package br.com.marbetramon.carstoreapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.com.marbetramon.carstoreapp.R;
import br.com.marbetramon.carstoreapp.helper.CarStoreSharedPreference;
import br.com.marbetramon.carstoreapp.model.Cars;

public class CarDetailsActivity extends AppCompatActivity {
    private static final String TAG = CarDetailsActivity.class.getSimpleName();

    Cars car;
    private List<Cars> listCars = new ArrayList<>();

    TextView carBrand, carPrice, carDescription, carStockQuantity;
    EditText etQuantity;
    ImageView carImage;
    Button addCartButton;

    private Gson gson;

    private int cartProductNumber = 0;

    private CarStoreSharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        getSupportParentActivityIntent();
//        setTitle(getIntent().getStringExtra( EXTRA_CARRO ));

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /* Set the proper image and text */
        carBrand = findViewById(R.id.tv_brand_car);
        carImage = findViewById(R.id.image_car_detail);
        carDescription = findViewById(R.id.tv_car_description);
        carPrice = findViewById(R.id.tv_car_price);
        carStockQuantity = findViewById(R.id.tv_stock_qtd);
        etQuantity = findViewById(R.id.et_quantity_detail);

        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();

        String productInStringFormat = getIntent().getExtras().getString("PRODUCT");
        final Cars singleProduct = gson.fromJson(productInStringFormat, Cars.class);

        if (singleProduct != null) {

            setTitle(singleProduct.getNome());
            carBrand.setText(singleProduct.getMarca());
            carStockQuantity.setText(Integer.toString(singleProduct.getQuantidade()));
            carPrice.setText(MessageFormat.format("R$ {0}",singleProduct.getPreco()));
            carDescription.setText(singleProduct.getDescricao());

            Picasso.get()
                    .load(singleProduct.getImagem())
                    .error(R.drawable.car_photo_unavailable)
                    .into(carImage);
        }

//            carStockQuantity.setText("Atualmente no Carrinho: "
//                    + ShoppingCartHelper.getProductQuantity(singleProduct));

            addCartButton = findViewById(R.id.btn_add_cart_details);
            addCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* verifica se a quantidade é valida */
                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(etQuantity.getText()
                                .toString());
                        if (quantity <= 0) {
                            Toast.makeText(getBaseContext(),
                                    "Por favor, altere a quantidade de items para adicionar ao carrinho.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(),
                                "Por favor, digite um numero.",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Toast.makeText(getBaseContext(),
                            "Item adicionado ao Carrinho.",
                            Toast.LENGTH_SHORT).show();

                    String productsFromCart = sharedPreference.retrieveProductFromCart();
                    if(productsFromCart.equals("")){
                        List<Cars> cartProductList = new ArrayList<Cars>();
                        cartProductList.add(singleProduct);
                        String cartValue = gson.toJson(cartProductList);
                        sharedPreference.addProductToTheCart(cartValue);
                        cartProductNumber = cartProductList.size();
                    }else{
                        String productsInCart = sharedPreference.retrieveProductFromCart();
                        Cars[] storedProducts = gson.fromJson(productsInCart, Cars[].class);

                        List<Cars> allNewProduct = convertObjectArrayToListObject(storedProducts);
                        allNewProduct.add(singleProduct);
                        String addAndStoreNewProduct = gson.toJson(allNewProduct);
                        sharedPreference.addProductToTheCart(addAndStoreNewProduct);
                        cartProductNumber = allNewProduct.size();
                    }
                    sharedPreference.addProductCount(cartProductNumber);
                    invalidateCart();
                }
            });

                    finish();
        }

    private List<Cars> convertObjectArrayToListObject(Cars[] allProducts){
        List<Cars> mProduct = new ArrayList<Cars>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private void invalidateCart() {
        invalidateOptionsMenu();
    }
}
