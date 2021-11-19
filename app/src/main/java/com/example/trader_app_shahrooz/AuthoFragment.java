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


public class AuthoFragment extends Fragment {


    View view;
    EditText  string_Autho;
    Activity activity;

    public AuthoFragment(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_autho, container, false);
        string_Autho = view.findViewById(R.id.Authorization);
        SharedPreferences prefs = activity.getSharedPreferences("Authorization", MODE_PRIVATE);

        string_Autho.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SharedPreferences.Editor editor = activity.getSharedPreferences("Authorization", MODE_PRIVATE).edit();
                editor.putString("Autho",string_Autho.getText().toString().trim());
                editor.apply();
            }
        });

        String Autho = prefs.getString("Autho", "");
        string_Autho.setText(Autho);


        return view;
    }


}