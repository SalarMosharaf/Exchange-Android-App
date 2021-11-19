package com.example.trader_app_shahrooz;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class App extends Application {

    public static final String CHANNEl_id = "ServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }


    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel =new NotificationChannel(CHANNEl_id,"ExampleService"
            , NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);

        }

    }


}
