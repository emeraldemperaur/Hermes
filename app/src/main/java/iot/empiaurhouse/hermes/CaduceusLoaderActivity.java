package iot.empiaurhouse.hermes;

import android.app.Activity;
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


public class CaduceusLoaderActivity extends Activity {

    ImageView HermesInitLogo;
    TypeWriterTextView QueryProgressText;
    TextView QueryCityText;

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


        //HermesInitLogo.setVisibility(View.INVISIBLE);
        final TypeWriterTextView QueryProgressText = findViewById(R.id.query_progress_text);
        QueryProgressText.setVisibility(View.INVISIBLE);
        HermesInitLogo.setVisibility(View.INVISIBLE);
        LoaderImgFX();

        Runnable _HermesQueryLoaderRunnable = new Runnable() {
            public void run() {
                HermesInitLogo.setVisibility(View.VISIBLE);
                QueryProgressText.setVisibility(View.VISIBLE);
                LoaderImgFX();
                LoaderTextFX();



            }
        };
        Handler _HermesQueryHandler = new Handler();
        _HermesQueryHandler.postDelayed(_HermesQueryLoaderRunnable, 1222);






    }




    public void LoaderImgFX(){

        final Animation Blinker = AnimationUtils.loadAnimation(this, R.anim.blinker);
        HermesInitLogo.startAnimation(Blinker);



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
        LoaderImgFX();

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
