package br.com.marbetramon.carstoreapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.marbetramon.carstoreapp.R;

public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {

    public TextView quantity, productName, productPrice, removeProduct;

    public ShoppingCartViewHolder(View itemView) {
        super(itemView);

        quantity = itemView.findViewById(R.id.tv_quantity_shop_cart);
        productName = itemView.findViewById(R.id.tv_name_car_shop_cart);
        productPrice = itemView.findViewById(R.id.tv_price_shop_cart);
//        removeProduct = itemView.findViewById(R.id.remove_from_cart);
    }
}
