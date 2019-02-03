package iot.empiaurhouse.hermes;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

public class LauncherActivity extends Activity {


    //launcher ui elements initialization
    ImageView ehlogo_bw;
    ImageView ehlogo_wb;
    LinearLayout hermes_launcheractivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        ehlogo_bw = findViewById(R.id.empiaurhouse_BWlogo);
        ehlogo_wb = findViewById(R.id.empiaurhouse_WBlogo);
        hermes_launcheractivity = findViewById(R.id.launcher_activity);

        //animation var initialization
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.faderone);
        final Animation animationFadeInB = AnimationUtils.loadAnimation(this, R.anim.fadertwo);


        Runnable _activitycoloranimationRunnable = new Runnable() {
            public void run() {
                //animate activity background and ui elements

                ehlogo_bw.setVisibility(View.INVISIBLE);

                int colorFrom = getResources().getColor(R.color.white);
                int colorTo = getResources().getColor(R.color.black);
                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                colorAnimation.setDuration(750);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        hermes_launcheractivity.setBackgroundColor((int) animator.getAnimatedValue());
                    }

                });
                colorAnimation.start();
                NavShader();




                ehlogo_wb.setVisibility(View.VISIBLE);
                ehlogo_wb.startAnimation(animationFadeInB);

            }
        };
        Handler _animHandler = new Handler();
        _animHandler.postDelayed(_activitycoloranimationRunnable, 3215);


        Runnable _LaunchIntentRunnable = new Runnable() {
            public void run() {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    //Google Location Services Disabled
                    LoaderIntent();

                }
                else {

                    LoaderIntent();
                }


            }
        };
        Handler _LoaderIntentHandler = new Handler();
        _LoaderIntentHandler.postDelayed(_LaunchIntentRunnable, 10440);




    }



    public void LoaderIntent(){
        Intent intent = new Intent(this, LoaderActivity.class);
        startActivity(intent);


    }



    public void NavShader(){

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.black));


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



    @Override
    protected void onResume()
    {
        super.onResume();


    }




    @Override
    protected void onPause()
    {
        super.onPause();
        ehlogo_wb.clearAnimation();
        ehlogo_bw.clearAnimation();



    }






}
