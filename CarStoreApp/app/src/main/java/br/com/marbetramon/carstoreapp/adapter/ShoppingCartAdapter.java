package br.com.marbetramon.carstoreapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.marbetramon.carstoreapp.R;
import br.com.marbetramon.carstoreapp.model.Cars;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {

    private Context context;

    private List<Cars> mProductObject;


    public ShoppingCartAdapter(Context context, List<Cars> mProductObject) {
        this.context = context;
        this.mProductObject = mProductObject;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ShoppingCartViewHolder productHolder = new ShoppingCartViewHolder(layoutView);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(ShoppingCartViewHolder holder, int position) {
        //get product quantity
        holder.quantity.setText("1");
        holder.productName.setText(mProductObject.get(position).getNome());
        holder.productPrice.setText(String.valueOf(mProductObject.get(position).getPreco()) + " $");

        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Do you want to remove product from cart", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }
}
