package iot.empiaurhouse.hermes;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class LoaderActivity extends Activity {

    ImageView hermeslogo;
    TextView hermeslogo_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        final TypeWriterTextView IntroSubText = findViewById(R.id.title_subtext);
        IntroSubText.setCharacterDelay(190);
        IntroSubText.displayTextWithAnimation(getString(R.string.title_subtext));

        hermeslogo = findViewById(R.id.hermes_logo);
        hermeslogo_text = findViewById(R.id.title_text);

        Runnable _IntentLoaderRunnable = new Runnable() {
            public void run() {
                WelcomeIntent();
            }
        };
        Handler _LoaderIntentHandler = new Handler();
        _LoaderIntentHandler.postDelayed(_IntentLoaderRunnable, 6333);




    }



    public void WelcomeIntent(){
        Intent welcomeintent = new Intent(this, WelcomeActivity.class);
        startActivity(welcomeintent);


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
        hermeslogo_text.clearAnimation();




    }




}
