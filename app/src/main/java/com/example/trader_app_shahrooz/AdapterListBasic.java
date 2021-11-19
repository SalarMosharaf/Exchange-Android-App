package com.example.trader_app_shahrooz;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterListBasic extends RecyclerView.Adapter<AdapterListBasic.MyViewHolder> {

    private final List<String> items;
    private final List<String> symbol;

    private Context ctx;


    public AdapterListBasic(Context context, List<String> items,List<String> namad_symbol) {
        this.items = items;
        this.symbol = namad_symbol;
//        Toast.makeText(context, ""+items.size(), Toast.LENGTH_SHORT).show();
        ctx = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView radioButton;

        public MyViewHolder(final View view) {
             super(view);
             radioButton=view.findViewById(R.id.radiobtn);

        }


    }

    @NonNull
    @Override
    public AdapterListBasic.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_namad,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListBasic.MyViewHolder holder,  int position) {
//        Toast.makeText(ctx, ""+items, Toast.LENGTH_SHORT).show();
        holder.radioButton.setText(""+items.get(position));
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = ctx.getSharedPreferences("Authorization", MODE_PRIVATE).edit();
                editor.putString("namad",symbol.get(position));
                editor.apply();
                Toast.makeText(ctx, ""+symbol.get(position), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
//        Toast.makeText(ctx, ""+items.size(), Toast.LENGTH_SHORT).show();
        return items.size();
    }


}