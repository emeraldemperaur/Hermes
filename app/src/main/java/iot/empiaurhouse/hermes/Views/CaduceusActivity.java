package iot.empiaurhouse.hermes.Views;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import androidx.core.content.ContextCompat;
import iot.empiaurhouse.hermes.R;
import iot.empiaurhouse.hermes.Utils.TypeWriterTextView;

public class CaduceusActivity extends Activity {

    Spinner destinationspinner;

    TextView UserName;
    TextView Salutation;
    TextView HermesSpeechSubText;
    Integer Greeting;

    String Petasos_URL="http://petasosapi.herokuapp.com/destinationnames";
    ArrayList<String> DestinationNames;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caduceus);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        //window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.setStatusBarColor(Color.TRANSPARENT);
        UserName = findViewById(R.id.helloname_text);
        Salutation = findViewById(R.id.hello_text);
        HermesSpeechSubText = findViewById(R.id.hermes_speech_subtext);
        HermesSpeechSubText.setVisibility(View.INVISIBLE);
        SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
        String User_Name = HermesIO.getString("DisplayName", "Full Name");
        UserName.setText(User_Name);
        final Animation LoadFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);





        Runnable _HermesLineALoaderRunnable = new Runnable() {
            public void run() {
                final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
                HermesLine.setVisibility(View.VISIBLE);
                HermesLineA();



                Runnable _HermesSubLineLoaderRunnable = new Runnable() {
                    public void run() {


                       HermesSpeechSubText.startAnimation(LoadFadeIn);
                        HermesSpeechSubText.setVisibility(View.VISIBLE);


                    }
                };
                Handler _HermesSubLineHandler = new Handler();
                _HermesSubLineHandler.postDelayed(_HermesSubLineLoaderRunnable, 3666);



            }
        };
        Handler _HermesLineAHandler = new Handler();
        _HermesLineAHandler.postDelayed(_HermesLineALoaderRunnable, 2333);


        Runnable _HermesLineBLoaderRunnable = new Runnable() {
            public void run() {

                GreetingGenerator();

            }
        };
        Handler _HermesLineBHandler = new Handler();
        _HermesLineBHandler.postDelayed(_HermesLineBLoaderRunnable, 8666);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView HermesSearchView = findViewById(R.id.HermesSearch);
        HermesSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        HermesQueryInit();






    }



    private void loadSpinnerData(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);

                        JSONArray jsonArray = jsonObject.getJSONArray("destinationnames");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String petasos_destination = jsonObject1.getString("name");
                            DestinationNames.add(petasos_destination);

                        }
                     DestinationNames.add("Select your destination");
                    destinationspinner.setAdapter(new ArrayAdapter<String>(CaduceusActivity.this, android.R.layout.simple_spinner_dropdown_item, DestinationNames));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }




    public void HermesQueryInit(){
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String caduceus_query = intent.getStringExtra(SearchManager.QUERY).toLowerCase();
            caduceus_query = caduceus_query.substring(0,1).toUpperCase() + caduceus_query.substring(1).toLowerCase();
            SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
            SharedPreferences.Editor HermesIOeditor = HermesIO.edit();
            HermesIOeditor.putString("HermesQuery", caduceus_query);
            HermesIOeditor.apply();
            CaduceusLoaderIntent();


        }

    }




    public void CaduceusLoaderIntent(){

        Intent caduceusloaderintent = new Intent(this, CaduceusLoaderActivity.class);
        startActivity(caduceusloaderintent);
        finish();

    }



    private void SalutationFX(){

        final Animation SalutationFadeIn = AnimationUtils.loadAnimation(this, R.anim.faderone);
        final Animation SalutationSlideDown = AnimationUtils.loadAnimation(this, R.anim.slidedown);
        UserName.startAnimation(SalutationSlideDown);
        Salutation.startAnimation(SalutationFadeIn);



    }



    private void GreetingGenerator(){

        Random rand = new Random();
        Greeting = rand.nextInt(5) + 1;
        switch (Greeting) {

            case 1:
                HermesLineB();
                break;
            case 2:
                HermesLineC();
                break;
            case 3:
                HermesLineD();
                break;
            case 4:
                HermesLineE();
                break;
            case 5:
                HermesLineD();
                break;


        }



    }



    private void HermesLineA(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine1));

    }



    private void HermesLineB(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine2A));

    }


    private void HermesLineC(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine2B));

    }



    private void HermesLineD(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine2C));

    }


    private void HermesLineE(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine2D));

    }


    private void HermesLineF(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine2D));

    }




    @Override
    protected void onResume()
    {
        super.onResume();

       
        final Animation LoadFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        HermesSpeechSubText.startAnimation(LoadFadeIn);


    }




    @Override
    protected void onPause()
    {
        super.onPause();

        HermesSpeechSubText.clearAnimation();



    }



    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            this.moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }













}
