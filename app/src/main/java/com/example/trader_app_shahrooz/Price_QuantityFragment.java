package com.example.trader_app_shahrooz;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


public class Price_QuantityFragment extends Fragment {

    View view;
    EditText price , quantity;
    Activity activity;

    public Price_QuantityFragment(Activity activity) {
        this.activity =activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences prefs = activity.getSharedPreferences("Authorization", MODE_PRIVATE);

         view = inflater.inflate(R.layout.fragment_price__quantity, container, false);
        EditText edt_price = view.findViewById(R.id.price);
        EditText edt_quantity = view.findViewById(R.id.quantity);

        edt_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SharedPreferences.Editor editor = activity.getSharedPreferences("Authorization", MODE_PRIVATE).edit();
                editor.putString("price",edt_price.getText().toString().trim());
                editor.apply();
            }
        });

        edt_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SharedPreferences.Editor editor = activity.getSharedPreferences("Authorization", MODE_PRIVATE).edit();
                editor.putString("quantity",edt_quantity.getText().toString().trim());
                editor.apply();
            }
        });


        return view;
    }


}