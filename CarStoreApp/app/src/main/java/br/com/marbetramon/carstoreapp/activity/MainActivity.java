package br.com.marbetramon.carstoreapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.com.marbetramon.carstoreapp.adapter.CarsAdapter;
import br.com.marbetramon.carstoreapp.R;
import br.com.marbetramon.carstoreapp.model.Cars;
import br.com.marbetramon.carstoreapp.model.ListCars;
import br.com.marbetramon.carstoreapp.service.CarService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.nfc.NfcAdapter.EXTRA_ID;
import static br.com.marbetramon.carstoreapp.service.CarService.URL_BASE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Cars> carsList;
    private RecyclerView recyclerView;
    private CarsAdapter carsAdapter;
    private Context context;

    Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fabViewCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
                startActivity(viewShoppingCartIntent);
            }
        });

        carsAdapter = new CarsAdapter(MainActivity.this, getAllListCars());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_main);
        setupRecyclerView(recyclerView);
    }


    private List<Cars> getAllListCars() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CarService service = retrofit.create(CarService.class);

        Call<List<Cars>> call = service.listCars();

        call.enqueue(new Callback<List<Cars>>() {
            @Override
            public void onResponse(@NonNull Call<List<Cars>> call, @NonNull Response<List<Cars>> response) {
                if (response.isSuccessful()) {
                    //adicionar Loading
                    Log.d(TAG, "onResponse: " + "isSuccessful");
                    carsList = response.body();
                    Log.d(TAG, "onResponse: " + "list" + carsList);

                    if (carsList != null && carsList.size() > 0) {
                        carsAdapter.setData(carsList);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Cars>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                //adicionar falha
            }
        });

        return carsList;
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(carsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
