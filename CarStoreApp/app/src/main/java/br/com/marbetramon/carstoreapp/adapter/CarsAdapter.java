package br.com.marbetramon.carstoreapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import br.com.marbetramon.carstoreapp.R;
import br.com.marbetramon.carstoreapp.activity.CarDetailsActivity;
import br.com.marbetramon.carstoreapp.model.Cars;
import br.com.marbetramon.carstoreapp.model.ListCars;

public class CarsAdapter extends RecyclerView.Adapter<CarViewHolder> {
    private static final String TAG = CarsAdapter.class.getSimpleName();

    private List<Cars> listCars = new ArrayList<>();
    private List<Cars> carros;
//    private MainCallback callback;
    private Context context;
    private static Map<Cars, ListCars> cartMap = new HashMap<Cars, ListCars>();//teste

    public CarsAdapter(Context context, List<Cars> carros) {
        this.context = context;
        this.carros = carros;
    }

//    public void setCallback(MainCallback callback) {
//        this.callback = callback;
//    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        CarViewHolder productHolder = new CarViewHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {

        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);

        final Cars singleProduct = listCars.get(position);

        final int carroId = singleProduct.getId();
        int carroQuantidade = singleProduct.getQuantidade();

        holder.tvName.setText(singleProduct.getNome());
        holder.tvMarca.setText(singleProduct.getMarca());
        holder.tvPreco.setText(MessageFormat.format("R$ {0}", singleProduct.getPreco()));
        Picasso.get()
                .load(singleProduct.getImagem())
                .error(R.drawable.car_photo_unavailable)
                .into(holder.carImage);

        holder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(context, CarDetailsActivity.class);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                String stringObjectRepresentation = gson.toJson(singleProduct);

                productIntent.putExtra("PRODUCT", stringObjectRepresentation);
                context.startActivity(productIntent);
            }
        });
    }

    public void setData(List<Cars> list) {
        listCars = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listCars.size();
    }
}