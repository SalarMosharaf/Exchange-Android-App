package com.example.trader_app_shahrooz;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Namads extends Fragment {

    RecyclerView recyclerView;
    private AdapterListBasic mAdapter;
    public List<String> namads;
    public List<String> symboll;
    String Autho;
    public static final String TAG ="ExampleJobIntentservice";
    View view;
    Activity activity;

    public Namads(Activity activity) {
        this.activity =activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_namads, container, false);
        SharedPreferences prefs = activity.getSharedPreferences("Authorization", MODE_PRIVATE);
        namads = new ArrayList<>();
        symboll = new ArrayList<>();
        Loadnamads loadNamads = new Loadnamads();
        loadNamads.execute();


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        Autho = prefs.getString("Autho", "");

        return view;
    }


     class Loadnamads extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            RequestQueue requestQueue = Volley.newRequestQueue(activity);

            String url = "https://api.cl3.mofid.dev/easy/api/EasyFilter/data";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray jsonArray = null;
                            try {
                                try {
                                    jsonArray = response.getJSONArray("gridData");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject gridDatas = jsonArray.getJSONObject(i);

                                    String symbolPersian = gridDatas.getString("symbolFa");
                                    String symbol = gridDatas.getString("symbolISIN");
//                                Toast.makeText(Namads.this, ""+symbol, Toast.LENGTH_SHORT).show();
                                    namads.add(symbolPersian);
                                    symboll.add(symbol);

//                                textView.append(""+symbol);
                                }
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
                                recyclerView.setLayoutManager(layoutManager);
                                mAdapter = new AdapterListBasic(activity, namads , symboll);
                                recyclerView.setAdapter(mAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> params = new HashMap<>();
                    params.put("Host", "api.cl3.mofid.dev");
                    params.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0");
                    params.put("Accept", "application/json , UTF-8");
                    params.put("Accept-Language", "en-US,en;q=0.5");
                    params.put("Origin", "https://d.easytrader.emofid.com");
                    params.put("Referer", "https://d.easytrader.emofid.com/");

                    if(Autho != null) {
                        params.put("Authorization",Autho);
                    }

                    params.put("Connection", "keep-alive");
                    return params;
                }

            };
//            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(8000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            recyclerView.setText(namads.get(1));
        }


    }


}