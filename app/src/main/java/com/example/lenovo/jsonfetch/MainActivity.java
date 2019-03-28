package com.example.lenovo.jsonfetch;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
     TextView country,capital,currency;
     Button button;
     String url = "https://api.myjson.com/bins/tdyyb";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        capital=(TextView)findViewById(R.id.text2);
        country=(TextView)findViewById(R.id.text1);
        currency=(TextView)findViewById(R.id.text3);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        processJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                                 error.printStackTrace();
                    }
                });
                CustomRequest.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
            }
        });
    }

    public void processJSON(JSONObject response){
        try {
            JSONArray jsonArray = response.getJSONArray("world");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                String str1 = jsonObject.getString("country");
                String str2 = jsonObject.getString("capital");
                String str3 = jsonObject.getString("currency");
                country.append("\n\n"+str1);
                capital.append("\n\n"+str2);
                currency.append("\n\n"+str3);

            }
        }catch (JSONException e){
            e.printStackTrace(); 
        }

    }

}
