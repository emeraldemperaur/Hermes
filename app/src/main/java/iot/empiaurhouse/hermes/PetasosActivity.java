package iot.empiaurhouse.hermes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static java.util.ResourceBundle.clearCache;

public class PetasosActivity extends Activity {

    private ArrayList<SectionDataModel> allSampleData;

    TextView PetasosQueryCityName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petasos_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        PetasosQueryCityName = findViewById(R.id.QueryCityTitle);
        SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
        String PetasosQuery_City = HermesIO.getString("HermesQuery", "City Name");
        PetasosQueryCityName.setText(PetasosQuery_City);

        allSampleData = new ArrayList<>();
        createDummyData();

        FloatingActionButton caduceusfab = findViewById(R.id.caduceusfab);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.petasos_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);




        caduceusfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NQPrompt();
            }
        });




    }




    private void createDummyData() {
        for (int i = 1; i <= 20; i++) {
            SectionDataModel dm = new SectionDataModel();
            dm.setHeaderTitle("Section " + i);
            ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();
            for (int j = 1; j <= 20; j++) {
                singleItemModels.add(new SingleItemModel("Item " + j, "URL " + j));
            }
            dm.setAllItemInSection(singleItemModels);
            allSampleData.add(dm);
        }

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





    }




    public void NQPrompt(){

        SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
        String Query_Name = HermesIO.getString("HermesQuery", "Hermes Query");


        AlertDialog alertDialog = new AlertDialog.Builder(PetasosActivity.this).create();
        alertDialog.setTitle("Leaving " + Query_Name);
        alertDialog.setIcon(R.mipmap.hermesapplogo);
        alertDialog.setMessage(getString(R.string.NQText));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        NQIntent();

                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PetasosActivity.this, "Visit www.emekaegwim.com", Toast.LENGTH_LONG).show();
                        PetasosDetailsIntent();
                    }
                });
        alertDialog.show();



    }




    public void NQIntent(){


        Intent NewQueryIntent = new Intent(this, CaduceusActivity.class);
        startActivity(NewQueryIntent);
        overridePendingTransition(R.anim.viewfadein,R.anim.viewfadeout);
        finish();
        clearCache();
    }


    public void PetasosDetailsIntent(){


        Intent PetasosDetailsIntent = new Intent(this, PetasosDetailActivity.class);
        startActivity(PetasosDetailsIntent);

    }








}
