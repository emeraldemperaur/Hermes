package iot.empiaurhouse.hermes;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;



public class CaduceusLoaderActivity extends Activity {

    ImageView HermesInitLogo;
    TypeWriterTextView QueryProgressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.viewfadein,R.anim.viewfadeout);
        setContentView(R.layout.activity_caduceus_loader);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        HermesInitLogo = findViewById(R.id.hermes_init_logo);
        //HermesInitLogo.setVisibility(View.INVISIBLE);
        final TypeWriterTextView QueryProgressText = findViewById(R.id.query_progress_text);
        QueryProgressText.setVisibility(View.INVISIBLE);
        LoaderImgFX();

        Runnable _HermesQueryLoaderRunnable = new Runnable() {
            public void run() {
                //HermesInitLogo.setVisibility(View.VISIBLE);
                QueryProgressText.setVisibility(View.VISIBLE);
                LoaderTextFX();


            }
        };
        Handler _HermesQueryHandler = new Handler();
        _HermesQueryHandler.postDelayed(_HermesQueryLoaderRunnable, 1222);






    }




    public void LoaderImgFX(){

        final Animation HermesInitBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        HermesInitLogo.startAnimation(HermesInitBounce);
        HermesInitBounce.setRepeatCount(Animation.INFINITE);


    }





    public void LoaderTextFX(){

        final TypeWriterTextView QueryProgressText = findViewById(R.id.query_progress_text);
        QueryProgressText.setCharacterDelay(190);
        QueryProgressText.displayTextWithAnimation(getString(R.string.LoadingText));

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




    }

    @Override
    public void onStart() {
        super.onStart();

    }




















}
