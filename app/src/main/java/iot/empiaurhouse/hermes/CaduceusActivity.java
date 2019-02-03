package iot.empiaurhouse.hermes;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class CaduceusActivity extends Activity {

    TextView UserName;
    TextView HermesSpeechSubText;

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

                HermesLineB();

            }
        };
        Handler _HermesLineBHandler = new Handler();
        _HermesLineBHandler.postDelayed(_HermesLineBLoaderRunnable, 8666);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView HermesSearchView = findViewById(R.id.HermesSearch);
        HermesSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));





    }





    public void HermesLineA(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine1));

    }



    public void HermesLineB(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine2A));

    }


    public void HermesLineC(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine2B));

    }



    public void HermesLineD(){

        final TypeWriterTextView HermesLine = findViewById(R.id.hermers_speech_sim);
        HermesLine.setCharacterDelay(190);
        HermesLine.displayTextWithAnimation(getString(R.string.HermesLine2C));

    }


    public void HermesLineE(){

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













}
