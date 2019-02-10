package iot.empiaurhouse.hermes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class PetasosActivity extends Activity {

    TextView PetasosQueryCityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petasos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        PetasosQueryCityName = findViewById(R.id.QueryCityTitle);
        SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
        String PetasosQuery_City = HermesIO.getString("HermesQuery", "City Name");
        PetasosQueryCityName.setText(PetasosQuery_City);
        final Animation titlefader = AnimationUtils.loadAnimation(this, R.anim.fadein);
        PetasosQueryCityName.startAnimation(titlefader);



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
        PetasosQueryCityName.clearAnimation();




    }





}
