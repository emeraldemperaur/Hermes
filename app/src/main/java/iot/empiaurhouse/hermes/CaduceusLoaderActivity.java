package iot.empiaurhouse.hermes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class CaduceusLoaderActivity extends Activity {

    ImageView HermesInitLogo;
    TypeWriterTextView QueryProgressText;
    TextView QueryCityText;


    InterstitialAd mInterstitialAd;
    private InterstitialAd Hermesinterstitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.viewfadein,R.anim.viewfadeout);
        setContentView(R.layout.activity_caduceus_loader);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        HermesInitLogo = findViewById(R.id.hermes_init_logo);
        QueryCityText = findViewById(R.id.query_destination_text);
        SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
        String Query_City = HermesIO.getString("HermesQuery", "City Name");
        QueryCityText.setText(Query_City);
        final Animation fader = AnimationUtils.loadAnimation(this, R.anim.faderfx);
        QueryCityText.startAnimation(fader);


        //HermesInitLogo.setVisibility(View.INVISIBLE);
        final TypeWriterTextView QueryProgressText = findViewById(R.id.query_progress_text);
        QueryProgressText.setVisibility(View.INVISIBLE);
        HermesInitLogo.setVisibility(View.INVISIBLE);


        Runnable _HermesQueryLoaderRunnable = new Runnable() {
            public void run() {

                QueryProgressText.setVisibility(View.VISIBLE);
                LoaderImgFX();
                LoaderTextFX();



            }
        };
        Handler _HermesQueryHandler = new Handler();
        _HermesQueryHandler.postDelayed(_HermesQueryLoaderRunnable, 2200);



        Runnable _PetasosIntentRunnable = new Runnable() {
            public void run() {

                GoogleAdsInit();

            }
        };
        Handler _PetasosIntentHandler = new Handler();
        _PetasosIntentHandler.postDelayed(_PetasosIntentRunnable, 5555);




    }



    public void GoogleAdsInit(){

        AdRequest Hermes_adRequest = new AdRequest.Builder().build();
        Hermesinterstitial = new InterstitialAd(CaduceusLoaderActivity.this);
        Hermesinterstitial.setAdUnitId(getString(R.string.test_interstitial_ad_unit_id));
        Hermesinterstitial.loadAd(Hermes_adRequest);

        Hermesinterstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {

                displayInterstitial();
            }

            public void onAdClosed() {

                PetasosIntent();

            }

            public void onAdFailedToLoad(int errorCode) {



            }

        });


    }





    public void PetasosIntent(){


        Intent Petasosintent = new Intent(this, PetasosActivity.class);
        startActivity(Petasosintent);

    }













    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (Hermesinterstitial.isLoaded()) {
            Hermesinterstitial.show();
        }

    }





    public void LoaderImgFX(){

        final Animation pusher = AnimationUtils.loadAnimation(this, R.anim.push_up_in);
        HermesInitLogo.startAnimation(pusher);
        HermesInitLogo.setVisibility(View.VISIBLE);




    }





    public void LoaderTextFX(){

        final TypeWriterTextView QueryProgressText = findViewById(R.id.query_progress_text);
        QueryProgressText.setCharacterDelay(190);
        QueryProgressText.displayTextWithAnimation(getString(R.string.ResultsFetchText));

    }




    @Override
    protected void onResume()
    {
        super.onResume();


    }




    @Override
    protected void onPause()
    {
        super.onPause();
        HermesInitLogo.clearAnimation();
        QueryCityText.clearAnimation();




    }

    @Override
    public void onStart() {
        super.onStart();

    }




















}
