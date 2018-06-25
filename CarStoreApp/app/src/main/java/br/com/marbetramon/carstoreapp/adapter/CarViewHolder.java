package br.com.marbetramon.carstoreapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.marbetramon.carstoreapp.R;

public class CarViewHolder extends RecyclerView.ViewHolder {

    public View contentLayout;
    public TextView tvName, tvMarca, tvPreco;
    public ImageView carImage;
    public CardView cardView;

    public CarViewHolder(View itemView) {
        super(itemView);
         contentLayout = itemView.findViewById(R.id.layout_content);
         tvName = itemView.findViewById(R.id.tv_car_name_main);
         tvMarca = itemView.findViewById(R.id.tv_brand_main);
         tvPreco = itemView.findViewById(R.id.tv_price_main);
         carImage = itemView.findViewById(R.id.image_car_main);
         cardView = itemView.findViewById(R.id.card_view);
    }
}
