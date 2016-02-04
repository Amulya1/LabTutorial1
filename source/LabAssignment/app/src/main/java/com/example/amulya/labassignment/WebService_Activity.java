package com.example.amulya.labassignment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebService_Activity extends AppCompatActivity {

    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void convertID (View v)
    {
        EditText Usd = (EditText)findViewById(R.id.editText3);
        final TextView Rupee = (TextView)findViewById(R.id.textView11111);
        String usd1 = Usd.getText().toString();
        final float j=Float.parseFloat(usd1);
        final String response1 = "";
        OkHttpClient client = new OkHttpClient();

        try {

            Request request = new Request.Builder()
                    .url("http://www.apilayer.net/api/live?access_key=ea8c47c3afa2f436eba15ab7935801cd&format=1")
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }

                private void hideKeyboard(View editableView) {
                    InputMethodManager imm = (InputMethodManager)mContext
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editableView.getWindowToken(), 0);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();

                    try {
                        jsonResult = new JSONObject(result);
                        JSONObject sample;
                        sample = jsonResult.getJSONObject("quotes");
                        //String s1 = jsonResult.getString("USDINR");
                                 String val=sample.getString("USDINR");
                              float i=Float.parseFloat(val);
                        final float k= i*j;
                        final String s=Float.toString(k);

//
                        Log.d("okHttp", "i");
                        Log.i("amulya",Float.toString(k));

                       // new AlertDialog.Builder(this).setTitle("dfjgb").setTitle(jsonResult.toString()).setNeutralButton("Close",null).show();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               // Log.d("dsd","sdsd");
                               hideKeyboard(Rupee);
                               Rupee.setText(s);
                            }
                        });
                     //   Log.d("dsd", "qwerrrrrr");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });


        }  catch (Exception ex) {
            // outputTextView.setText(ex.getMessage());
        }

    }
}