package com.example.ex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class WeatherActivity extends AppCompatActivity {

    TextView t1_temp, t2_city, t3_description, t4_data,t5_location;
    ImageView imageView;
    String my_longitude;
    String my_latitude;

    private String imgURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        t1_temp = (TextView)findViewById(R.id.textView);
        t2_city = (TextView)findViewById(R.id.textView1);
        t3_description = (TextView)findViewById(R.id.textView2);
        t4_data = (TextView)findViewById(R.id.textView3);
        imageView = (ImageView)findViewById(R.id.imageView);
        t5_location = (TextView)findViewById(R.id.textView4);

        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


                if ( Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( WeatherActivity.this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                            0 );
                }
                else{
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    String provider = location.getProvider();
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    double altitude = location.getAltitude();
                    my_longitude = Double.toString(longitude);
                    my_latitude= Double.toString(latitude);



                    t5_location.setText("위치정보 : " + provider + "\n" +
                            "위도 : " + longitude + "\n" +
                            "경도 : " + latitude + "\n" +
                            "고도  : " + altitude);

                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            10000,
                            1,
                            gpsLocationListener);
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            1000,
                            1,
                            gpsLocationListener);
                }

        find_weather();


    }
    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            t5_location.setText("위치정보 : " + provider + "\n" +
                    "위도 : " + longitude + "\n" +
                    "경도 : " + latitude + "\n" +
                    "고도  : " + altitude);

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };



    private void find_weather() {
        //String url = "http://api.openweathermap.org/data/2.5/weather?q=seoul&appid=449090d450442665f23f613cc903f504&units=metric";
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + my_latitude + "&lon="+ my_longitude+
                "&appid=449090d450442665f23f613cc903f504&units=metric";
        Log.e("url",url);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city = response.getString("name");
                    String icon = object.getString("icon");
                    Log.e("icon",icon);
                    imgURL = "http://openweathermap.org/img/w/" + icon + ".png";
                    Log.e("icon url",imgURL);
                    //Uri myUri = Uri.parse(imgURL);
                    //

                    t1_temp.setText(temp);
                    t2_city.setText(city);
                    t3_description.setText(description);
                    Picasso.with(getApplicationContext()).load(Uri.parse(imgURL)).into(imageView);
                    //imageView.setImageURI(myUri);
                    //imageView.setImageResource(myUri);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE-MM-dd");
                    String formatte_date = sdf.format(calendar.getTime());

                    t4_data.setText(formatte_date);

//                    double temp_int = Double.parseDouble(temp);
//                    double centi = (temp_int - 32)/1.8000;
//                    centi = Math.round(centi);
//                    int i = (int)centi;
//                    t1_temp.setText(String.valueOf(i));


                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            private void into(ImageView imageView) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }
}
