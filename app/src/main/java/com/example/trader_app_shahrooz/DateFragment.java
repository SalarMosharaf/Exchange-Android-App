package com.example.trader_app_shahrooz;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ir.mirrajabi.persiancalendar.PersianCalendarView;
import ir.mirrajabi.persiancalendar.core.interfaces.OnDayClickedListener;
import ir.mirrajabi.persiancalendar.core.models.PersianDate;


public class DateFragment extends Fragment {

    public static final String TAG ="ExampleJobIntentservice";
    View view;
    Activity activity;

    public DateFragment (Activity activity) {
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
        view = inflater.inflate(R.layout.fragment_date, container, false);

        PersianCalendarView calendarView  = (PersianCalendarView) view.findViewById(R.id.calendar);
        calendarView.setVisibility(View.VISIBLE);
        calendarView.setOnDayClickedListener(new OnDayClickedListener() {
            @Override
            public void onClick(PersianDate persianDate) {
                Log.d(TAG, "day: "+persianDate.getDayOfMonth());
                SharedPreferences.Editor editor = activity.getSharedPreferences("Authorization", MODE_PRIVATE).edit();
                editor.putInt("dayChosed",persianDate.getDayOfMonth());
                editor.putInt("monthChosed",persianDate.getMonth());
                editor.apply();
            }
        });
        return view;
    }


}