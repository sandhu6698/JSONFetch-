package com.example.lenovo.jsonfetch;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CustomRequest {
    private static CustomRequest rInstance;
    private RequestQueue requestQueue;
    private static Context mContext;


    private CustomRequest(Context context){
        mContext=context;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue(){
         if (requestQueue==null){
             requestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        }
         return  requestQueue;
    }

    public static synchronized CustomRequest getInstance(Context context){
        if (rInstance==null){
            rInstance= new CustomRequest(context);
        }
      return rInstance;
    }

  public <T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
  }
}
