package iot.empiaurhouse.hermes.Views;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

import androidx.core.app.ActivityCompat;
import iot.empiaurhouse.hermes.R;
import iot.empiaurhouse.hermes.Views.SignInActivity;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.RECORD_AUDIO;

public class WelcomeActivity extends Activity {

    private static final int PERMISSION_REQUEST_CODE = 200;

    TextView Greeting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        Greeting = findViewById(R.id.greeting_text);
        GREETER();
        final Animation LoadFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        requestPermission();
        Greeting.startAnimation(LoadFadeIn);



    }


    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO, ACCESS_FINE_LOCATION ,READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);


    }




    public void GREETER(){


        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        String greeting = null;
        if(hour>= 12 && hour < 17){
            greeting = "Good Afternoon";
            Greeting.setText(greeting);
        } else if(hour >= 17 && hour < 21){
            greeting = "Good Evening";
            Greeting.setText(greeting);
        } else if(hour >= 21 && hour < 24){
            greeting = "Carpe Noctem";
            Greeting.setText(greeting);
        } else {
            greeting = "Good Morning";
            Greeting.setText(greeting);
        }



    }



    public void SIGNIN_BTN(View view) {

            Intent signinintent = new Intent(this, SignInActivity.class);
            startActivity(signinintent);

    }






    public void EXPLORE_BTN(View view) {

        String url = "https://www.empiaurhouse.com";
        Intent eh = new Intent(Intent.ACTION_VIEW);
        eh.setData(Uri.parse(url));
        startActivity(eh);

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
        GREETER();
        final Animation LoadFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Greeting.startAnimation(LoadFadeIn);

    }




    @Override
    protected void onPause()
    {
        super.onPause();
        Greeting.clearAnimation();



    }








}
