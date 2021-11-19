package com.example.trader_app_shahrooz;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button btn_set , btn;
    public String single_choice_selected;

    public static final String TAG ="ExampleJobIntentservice";
    JSONObject jsonObject;

    String SelectedNamad;
    Button namad_btn , preview_btn ,next_btn;
    StepView stepView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preview_btn = (Button)findViewById(R.id.btn_prev);
        next_btn = (Button)findViewById(R.id.btn_next);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent2 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent2, 0);
            }
        }
        replacefragment(new AuthoFragment(MainActivity.this));
        SharedPreferences prefs = getSharedPreferences("Authorization", MODE_PRIVATE);
        stepView =(StepView)findViewById(R.id.stepView);

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.framelayout);

        if (f instanceof AuthoFragment) {
            preview_btn.setVisibility(View.INVISIBLE);
        }



//        btn_set = findViewById(R.id.btn_set);
////        namad_btn = findViewById(R.id.resault_json);


//        namad_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent_namad = new Intent(MainActivity.this , Namads.class);
//
//                    intent_namad.putExtra("Autho", string_Autho.getText().toString().trim());
//                startActivity(intent_namad);
//            }
//        });
//        String choosed_namad = getIntent().getStringExtra("choosed_namad");


        preview_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.framelayout);
                if(f instanceof Namads) {
                    stepView.setStep(4);
                    next_btn.setVisibility(View.VISIBLE);
                    next_btn.setText("مرحله بعد");
                    replacefragment(new Price_QuantityFragment(MainActivity.this));
                }
                if(f instanceof Price_QuantityFragment) {
                    stepView.setStep(3);
                    replacefragment(new ClockFragment(MainActivity.this));
                }
                if(f instanceof ClockFragment) {
                    stepView.setStep(2);
                    replacefragment(new DateFragment(MainActivity.this));
                }
                if(f instanceof DateFragment) {
                    stepView.setStep(1);
                    preview_btn.setVisibility(View.INVISIBLE);
                    replacefragment(new AuthoFragment(MainActivity.this));
                }

            }
        });


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.framelayout);
                if(f instanceof AuthoFragment) {
                    stepView.setStep(2);
                    replacefragment(new DateFragment(MainActivity.this));
                    preview_btn.setVisibility(View.VISIBLE);
                }
                if(f instanceof DateFragment) {
                    stepView.setStep(3);
                    replacefragment(new ClockFragment(MainActivity.this));
                }
                if(f instanceof ClockFragment) {
                    stepView.setStep(4);
                    replacefragment(new Price_QuantityFragment(MainActivity.this));
                }
                if(f instanceof Price_QuantityFragment) {
                    stepView.setStep(5);
                    next_btn.setText("ارسال");
                    replacefragment(new Namads(MainActivity.this));
                }
                if(next_btn.getText().toString().trim().equals("ارسال") && !prefs.getString("price", "").equals("") && !prefs.getString("namad", "").equals("")) {
                    stepView.setStep(6);
                    String Autho = prefs.getString("Autho", "");
                    String houre_chossed = prefs.getString("houre_chossed", "");
                    String minute_chossed = prefs.getString("minute_chossed", "");
                    String price = prefs.getString("price", "");
                    String quantity = prefs.getString("quantity", "");
                    int dayChosed = prefs.getInt("dayChosed", 0);
                    int MonthChosed = prefs.getInt("monthChosed", 0);

                    if(!Autho.equals("") && !houre_chossed.equals("") && !minute_chossed.equals("") && !price.equals("") && !quantity.equals("") && dayChosed != 0 && MonthChosed != 0) {

                        Intent intent = new Intent(MainActivity.this, BackGroundProcess.class);
                        intent.putExtra("chosed_date_time", dayChosed);
                        intent.putExtra("monthChosed", MonthChosed);
                        intent.putExtra("chosed_time_hour", houre_chossed);
                        intent.putExtra("chosed_time_minute", minute_chossed);
//                intent.putExtra("chosed_namad", choosed_namad);
                        intent.putExtra("Autho", Autho);
                        intent.putExtra("quantity", quantity);
                        intent.putExtra("price", price);
//                ContextCompat.startForegroundService(MainActivity.this,intent);
                        BackGroundProcess backGroundProcess = new BackGroundProcess();
                        backGroundProcess.enqueuwork(MainActivity.this,intent);

                    } else {
                        Toast.makeText(MainActivity.this, "زن عمو یه فیلدی رو تنظیم نکردی!!!", Toast.LENGTH_SHORT).show();
                    }



                    Toast.makeText(MainActivity.this, ""+Autho+"----- "+houre_chossed+"----"+minute_chossed+"---"+price+"---"+quantity+"---"+dayChosed+"---"+MonthChosed, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences("Authorization", MODE_PRIVATE).edit();
                    editor.putString("price","");
                    editor.putString("price","");
                    editor.apply();
                }


            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == 0) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(MainActivity.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }



    private void replacefragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();

    }


    public void showCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.activity_dialog);

        //Initializing the views of the dialog.
        Button submitButton = dialog.findViewById(R.id.btnOk);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


//        soundPlayer= MediaPlayer.create(c, R.raw.sound);
//        soundPlayer.start();
//        soundPlayer.setLooping(true);

        dialog.show();
    }


}