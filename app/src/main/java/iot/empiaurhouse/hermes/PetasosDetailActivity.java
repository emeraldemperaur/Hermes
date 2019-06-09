package iot.empiaurhouse.hermes;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class PetasosDetailActivity extends Activity {

    TypeWriterTextView PDItemText;
    ImageView PDBackIcon;
    ImageView PDImage;
    TextView  PDSynopsisText;
    TextView  PDWebsite;
    TextView  PDTelephone;
    TextView  PDEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petasos_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        PDItemText = findViewById(R.id.pdbanner_itemtext);
        PDBackIcon = findViewById(R.id.pd_back_icon);
        PDImage = findViewById(R.id.pd_banner_img);
        PDSynopsisText = findViewById(R.id.synopsis_details_text);
        PDWebsite = findViewById(R.id.website_details_text);
        PDTelephone = findViewById(R.id.telephone_details_text);
        PDEmail = findViewById(R.id.email_details_text);
        PDItemTextFX(getString(R.string.sample_title),190);
        BackButtonInit();


    }



    private void BackButtonInit(){

        Animation SalutationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        PDBackIcon.startAnimation(SalutationFadeIn);


    }



    private void PDItemTextFX(String PDTitle, Integer Timer){

        PDItemText.setCharacterDelay(Timer);
        PDItemText.displayTextWithAnimation(PDTitle);

    }


    private void ItemInfoTextSetter(String Synopsis, String Website, String Telephone, String Email){

        PDSynopsisText.setText(Synopsis);
        PDWebsite.setText(Website);
        PDTelephone.setText(Telephone);
        PDEmail.setText(Email);

    }




    @Override
    protected void onResume()
    {
        super.onResume();
        BackButtonInit();
        ItemInfoTextSetter("Hermes is a simple destination information demo application built for the Android mobile operating system to proffer end-users with supposedly relevant locale data and other trivial traveller insights.","www.emekaegwim.com","+1(778)268-0948","egwim.emeka@gmail.com");



    }




    @Override
    protected void onPause()
    {
        super.onPause();





    }


    public void BCK_BTN(View view) {

        finish();

    }



}
