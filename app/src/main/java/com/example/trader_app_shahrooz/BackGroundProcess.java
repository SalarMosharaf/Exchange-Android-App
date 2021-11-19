package com.example.trader_app_shahrooz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class BackGroundProcess extends JobIntentService {

    public int SEND_REQUEST = 0;
    public int InternetReminder = 0;
    public Long Delay_Handler = 5000L;
    int stop = 0;
    int stopp = 0;
    JSONObject jsonObject;
    public Context context;
    public static final String TAG ="ExampleJobIntentservice";
    
//    private Notification notification;

    void enqueuwork(Context context ,Intent work) {
        enqueueWork(context , BackGroundProcess.class , 123, work);
        this.context =  context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Intent notificationIntent = new Intent(this , MainActivity.class);
//        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,notificationIntent,0);
//
//        notification =new NotificationCompat.Builder(this,CHANNEl_id)
//                .setContentTitle("title")
//                .setContentText("content")
//                .setContentIntent(pendingIntent)
//                .build();
//
//        final String chosedDateTime = intent.getStringExtra("chosed_date_time");
//        final String chosedTimeHoure = intent.getStringExtra("chosed_time_hour");
//        final String chosedTimeMinute = intent.getStringExtra("chosed_time_minute");
////        final String chosedNamad = intent.getStringExtra("chosed_namad");
//        final String Autho = intent.getStringExtra("Autho");
//        final String quantity = intent.getStringExtra("quantity");
//        final String price = intent.getStringExtra("price");
//
//         final Handler handler = new Handler();
//         Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                PersianCalendar persianCalendar = new PersianCalendar();
//                String date = persianCalendar.getPersianShortDate();
//
//                String currentTime = new SimpleDateFormat("HH:m", Locale.getDefault()).format(new Date());
//                int int_currentMinuteTime = Integer.parseInt(new SimpleDateFormat("m", Locale.getDefault()).format(new Date()));
////                Toast.makeText(BackGroundProcess.this, "current :"+currentTime, Toast.LENGTH_SHORT).show();
//                if(currentTime.startsWith("0")) {
//                    currentTime = currentTime.substring(1);
//                }
//
//
//
//                if (chosedDateTime != null && date.equals(chosedDateTime.substring(0, 10))) {
//
//
//                    if (InternetReminder == 0 & currentTime.equals("11:59")) {
//                        Toast.makeText(BackGroundProcess.this, "Internet Reminder", Toast.LENGTH_SHORT).show();
//                        InternetReminder = 1;
//                        Intent intent1 = new Intent(BackGroundProcess.this, Dialog.class);
//                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent1);
//                    }
//                    Delay_Handler = 1000L;
//
////                    if(int_currentMinuteTime == Integer.parseInt(chosedTimeMinute)-1){
////                        Toast.makeText(BackGroundProcess.this, "ollll", Toast.LENGTH_SHORT).show();
////                    }
//                    if (currentTime.equals(chosedTimeHoure + ":" + chosedTimeMinute)) {
//
//                        //                        InternetReminder = 0;
//                        if (SEND_REQUEST == 0) {
//                            for (int i = 0; i < 1; i++) {
//                                //try send two request
//                                postRequest("", price , quantity, chosedDateTime,Autho);
//                                Toast.makeText(BackGroundProcess.this, "sending request", Toast.LENGTH_SHORT).show();
//                            }
//                            SEND_REQUEST = 1;
//                            //                            stopForeground(true);
//                            stopSelf();
//
//                        }
//                    }
//                }
//
////                if(SEND_REQUEST == 1 ) {
////                    stopForeground(true);
////                    stopSelf(1);
//////                    android.os.Process.killProcess(android.os.Process.myPid());
////                }
//
//                handler.postDelayed(this, Delay_Handler);
//
//            }
//        };
//
////        handler.removeCallbacks(runnable);
//
//        handler.post(runnable);
//
//        startForeground(1,notification);
//
//        return START_NOT_STICKY;
//    }



//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }


    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        final int chosedDay = intent.getIntExtra("chosed_date_time",0);
        final int chosedMonth = intent.getIntExtra("monthChosed",0);
        final String chosedTimeHoure = intent.getStringExtra("chosed_time_hour");
        final String chosedTimeMinute = intent.getStringExtra("chosed_time_minute");
//        final String chosedNamad = intent.getStringExtra("chosed_namad");
        final String Autho = intent.getStringExtra("Autho");
        final String quantity = intent.getStringExtra("quantity");
        final String price = intent.getStringExtra("price");


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


//                if (chosedDay != 0 && chosedDay == today.getDayOfMonth()) {

//                if (InternetReminder == 0 & currentTime.equals("11:59")) {
//                    Toast.makeText(BackGroundProcess.this, "Internet Reminder", Toast.LENGTH_SHORT).show();
//                    InternetReminder = 1;
//                    Intent intent1 = new Intent(BackGroundProcess.this, Dialog.class);
//                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent1);
//                }
                                RequestQueue requestQueue = Volley.newRequestQueue(BackGroundProcess.this);
                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                                        "http://api.timezonedb.com/v2.1/get-time-zone?key=4ZE3XHMX66OU&format=json&by=zone&fields=formatted&zone=Asia/Tehran", null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            String res = response.getString("formatted");

                                            if(res.substring(11, 19).equals("0"+chosedTimeHoure + ":" + chosedTimeMinute + ":00")){
                                                Log.d(TAG, "raft");
                                                SEND_REQUEST =1;
                                            }
                                            if(res.substring(11, 19).equals(chosedTimeHoure + ":" + chosedTimeMinute + ":00")){
                                                Log.d(TAG, "raft");
                                                SEND_REQUEST =1;
                                            }

                                            Log.d(TAG, "onResponse: "+res.substring(11,19));
                                            Log.d(TAG, "onchosedhg: "+"0"+chosedTimeHoure + ":" + chosedTimeMinute + ":00");
                                        } catch (JSONException e) {
                                            Log.d(TAG, "err: "+e.toString());
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(TAG, "err: "+error.toString());
                                    }
                                });

                                requestQueue.add(jsonObjectRequest);

                                if(SEND_REQUEST == 1) {

                                    Timer timer2 = new Timer();
                                    timer2.scheduleAtFixedRate(new TimerTask() {
                                        @Override
                                        public void run() {
                                            //try send two request
                                            postRequest("", price, quantity, "1400/"+chosedMonth+"/"+chosedDay, Autho);
                                            Log.d(TAG, "sending request");

                                            if(stop == 2 ) {
                                                timer2.cancel();
                                                timer.cancel();
                                            }
                                            stop++;

                                        }

                                    },0,300);
                                }

//                }

            }
        },0,1000);

        if(isStopped()) return;

    }



    public void postRequest(String choosedNamad , String pricee , String quantity , String date , String Autho) {

//        String url = "https://cl3.emofid.com/easy/api/OmsOrder";
        String url = "https://api.cl3.mofid.dev/easy/api/OmsOrder";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        jsonObject = new JSONObject();
        try {

//            jsonObject.put("isin", "IRO1VMDR0001");
            jsonObject.put("isin", "IRT1DARA0001");
            jsonObject.put("financeId", 1);
            jsonObject.put("quantity", quantity);
            jsonObject.put("price", pricee);
            jsonObject.put("side", 0);
            jsonObject.put("validityType", 74);
            jsonObject.put("validityDateJalali", date);
            jsonObject.put("easySource", 1);
            jsonObject.put("referenceKey", "2d47f7a6-02db-44d5-be7a-ece2cf867c6c");
            jsonObject.put("cautionAgreementSelected", false);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, "onResponse: "+response);
//                    Toast.makeText(BackGroundProcess.this, "" + response, Toast.LENGTH_LONG).show();


                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
//                    Toast.makeText(BackGroundProcess.this, "error : " + jsonObject, Toast.LENGTH_LONG).show();

                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> params = new HashMap<>();
//                    params.put("Host", "cl3.emofid.com");
                    params.put("Host", "api.cl3.mofid.dev");
                    params.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0");
//                    params.put("User-Agent","android");
//                    params.put("User-Agent","user-agent-string");
                    params.put("Accept","application/json , UTF-8");
                    params.put("Accept-Language", "en-US,en;q=0.5");
//                    params.put("Accept-Encoding", "gzip, deflate, br");
                    params.put("Content-Type", "application/json; charset=utf-8");
//                    params.put("Content-Length", "228");
                    params.put("Origin", "https://d.easytrader.emofid.com");
                    params.put("Referer", "https://d.easytrader.emofid.com/");
                    params.put("Authorization","Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImI3MmYyMjczZTE4YTQ0YjQ5OTFmMDg3ODIzNzQyYmI1IiwidHlwIjoiYXQrand0In0.eyJuYmYiOjE2MzYxODY1NzUsImV4cCI6MTYzNjI3NjU3NSwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50LmVtb2ZpZC5jb20iLCJhdWQiOlsiZWFzeTJfYXBpIiwiaHR0cHM6Ly9hY2NvdW50LmVtb2ZpZC5jb20vcmVzb3VyY2VzIl0sImNsaWVudF9pZCI6ImVhc3kyX2NsaWVudF9wa2NlIiwic3ViIjoiYTA2ZmNkZGItYTdlMC00ODYyLTk1ZjEtNTg1MThmZDIyOTU1IiwiYXV0aF90aW1lIjoxNjM2MTg2NTczLCJpZHAiOiJsb2NhbCIsInBrIjoiYTA2ZmNkZGItYTdlMC00ODYyLTk1ZjEtNTg1MThmZDIyOTU1IiwidHdvX2ZhY3Rvcl9lbmFibGVkIjoiZmFsc2UiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhMDZmY2RkYi1hN2UwLTQ4NjItOTVmMS01ODUxOGZkMjI5NTUiLCJuYW1lIjoiYTA2ZmNkZGItYTdlMC00ODYyLTk1ZjEtNTg1MThmZDIyOTU1IiwicGhvbmVfbnVtYmVyIjoiMDkzMzU2MDE5MDciLCJkaXNwbGF5X25hbWUiOiLZh9in2KzYsSDYp9mF24zZhtuMINmB2KfYsdiz2KfZhtuMIiwiZmlyc3RuYW1lIjoi2YfYp9is2LEiLCJsYXN0bmFtZSI6Itin2YXbjNmG24wg2YHYp9ix2LPYp9mG24wiLCJuYXRpb25hbF9pZCI6IjQ2NzkzNzQ0NzAiLCJuYXRpb25hbF9pZF92ZXJpZmllZCI6InRydWUiLCJjdXN0b21lcl9pc2luIjoiMTEyOTQ2NzkzNzQ0NzAiLCJjcmVhdG9yX2NsaWVudCI6Im15bW9maWQtbXZjIiwiY29udHJhY3QiOlsiVGVzdENvbnRyYWN0XzEuMCIsIkVjb250cmFjdF8yLjAiXSwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsImVhc3kyX2FwaSJdLCJhbXIiOlsicHdkIl19.Cot_ZZPPfROjQXJrT3D5NXDFrKoFZdHiLXVb8GdMzEhozY5fGDsUXMPFifLjEWsn1RnRzUogT3eSq403ZDf63KiACsLcZHdBDEHMzITWfATsx8jrfQWHTicB3blqy12KH3sVN5vVWWnZsJefdgxFCZIofG_fiLhYEf40nJ4nBnVMp3iObPMGEHUeCZKZT20g0z-c1CjeakwQzFTfson0XCRVvkmGkxofeQ-Mp0wVh7vCG7vczDaBb39Bov6AJC15qWVvxDwnjvRT4V3eAtEwjj_eFejd5tMEDv9ncdVti7MPZhRDX2JVtcF5zaUV1t09yboQys2AlIhmk3xQRrbVxg");
                    params.put("Connection", "keep-alive");
                    return params;
                }

            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(700,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onStopCurrentWork() {
        return super.onStopCurrentWork();
    }




}
