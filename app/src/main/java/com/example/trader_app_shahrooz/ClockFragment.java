package com.example.trader_app_shahrooz;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;


public class ClockFragment extends Fragment {


    View view;
    Activity activity;
    String chossed_time_hour , chossed_time_minute;

    public ClockFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar cur_calender = Calendar.getInstance();
        TimePickerDialog datePicker = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                chossed_time_hour = String.valueOf(hourOfDay);
                chossed_time_minute= String.valueOf(minute).trim();
                Toast.makeText(activity, "Main:"+chossed_time_hour+""+chossed_time_minute, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = activity.getSharedPreferences("Authorization", MODE_PRIVATE).edit();
                editor.putString("houre_chossed",chossed_time_hour);
                editor.putString("minute_chossed",chossed_time_minute);
                editor.apply();
            }
        }, cur_calender.get(Calendar.HOUR_OF_DAY), cur_calender.get(Calendar.MINUTE), true);
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.black));
        datePicker.show(activity.getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clock, container, false);

        return view;
    }

}