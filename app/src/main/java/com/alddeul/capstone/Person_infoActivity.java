package com.alddeul.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.sdk.user.UserApiClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Person_infoActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    int trigger;
    LinearLayout linearLayout;
    TextView name2, relation, age2, address, phone, email2, registration, toolbar;
    String name, email, role, vname, id;
    private RequestQueue queue, queue2;
    String TAG = "personinfo";
    ImageButton img;
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> map;
    private RequestQueue mqueue, mqueue2;
    MainActivity m = new MainActivity();
    EditText input_phone;
    Button input_button;
    LinearLayout input_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        input_phone = findViewById(R.id.input_phone);
        input_button = findViewById(R.id.input_button);
        input_button.setOnClickListener(this);

        intent = getIntent();
        id = intent.getStringExtra("id");
        role = intent.getStringExtra("Role");
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");


        queue2 = Volley.newRequestQueue(this);
        mqueue = Volley.newRequestQueue(this);
        mqueue2 = Volley.newRequestQueue(this);

        String url = m.serverip+"api/users/" + email;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.isEmpty()) {

                    return;
                } else {

                    try {
                        map = mapper.readValue(response, Map.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Log.d("", "" + response);
                    String phonenum;
                    phonenum = map.get("phoneNumber");


                    name2 = findViewById(R.id.name);

                    age2 = findViewById(R.id.age);

                    phone = findViewById(R.id.phon);
                    email2 = findViewById(R.id.emai);
                    /*registration = findViewById(R.id.regis);*/

                    name2.setText(name);
                    email2.setText(email);
                    phone.setText(phonenum);


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("infoguard", "no userdata");


            }
        }){
            @Override //response??? UTF8??? ??????????????? ????????????
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

        };

        stringRequest.setTag(TAG);
        mqueue2.add(stringRequest);






        /*linearLayout = findViewById(R.id.guard_info);*/
        queue2 = Volley.newRequestQueue(this);

        toolbar = findViewById(R.id.toolbar_title);
        toolbar.setText(vname + "??????" + name + "???");


        String url2 = m.serverip+"api/users/" + id;
/*
        Button villageadd = findViewById(R.id.villageadd);
        villageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Person_infoActivity.this, addvillage.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });*/
        img = findViewById(R.id.logout);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        return null;
                    }
                });


                Intent intent2 = new Intent(Person_infoActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });

        Button addcaregiver = findViewById(R.id.caregiveradd);
        addcaregiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == addcaregiver) {
                    if(input_layout.getVisibility() == View.VISIBLE)
                        input_layout.setVisibility(View.GONE);
                    else
                        input_layout.setVisibility(View.VISIBLE);
                }
//                Intent intent = new Intent(Person_infoActivity.this, caregiverjoinActivity.class);
//                intent.putExtra("id",id);
//                intent.putExtra("email",email);
//                 intent.putExtra("name",name);
//
//                startActivity(intent);

            }
        });

        Button delete = findViewById(R.id.guard_data);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest2 = new StringRequest(Request.Method.DELETE, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*tv.setText(response);*/
                        Log.d(" ", " ????????????");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.getMessage());

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("email", email);

                        return params;
                    }
                };


                stringRequest2.setTag(TAG);
                queue2.add(stringRequest2);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view == input_button) {
            ;
        }
    }
}