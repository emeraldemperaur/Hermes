package iot.empiaurhouse.hermes.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import iot.empiaurhouse.hermes.Adapters.PetasosRVAdapter;
import iot.empiaurhouse.hermes.Adapters.RecyclerViewDataAdapter;
import iot.empiaurhouse.hermes.Model.PlaceDataModel;
import iot.empiaurhouse.hermes.Model.SectionDataModel;
import iot.empiaurhouse.hermes.Model.SingleItemModel;
import iot.empiaurhouse.hermes.R;

import static java.util.ResourceBundle.clearCache;

public class PetasosActivity extends Activity {

    TextView Time;
    ImageView destBanner;
    String destBannerURL;
    TextView Currency;
    TextView CountryCode;
    TextView dividerlabel;
    TextView PetasosErrorText;
    TextView Weather;
    private PetasosRVAdapter petasosRVAdapter;

    private String PetasosQuery_City;
    private ArrayList<PlaceDataModel> dataModelArrayList;

    private RecyclerViewDataAdapter adapter;
    private RecyclerView recyclerView;
    TextView PetasosQueryCityName;
    private static ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_petasos_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

        recyclerView = findViewById(R.id.petasos_recycler_view);
        destBanner = findViewById(R.id.destination_banner_img);
        Currency = findViewById(R.id.localcurrency_label);
        Time = findViewById(R.id.localtime_label);
        CountryCode = findViewById(R.id.countrycode_label);
        Weather = findViewById(R.id.localweather_label);
        dividerlabel = findViewById(R.id.divider_label);
        PetasosErrorText = findViewById(R.id.petasos_error_text);

        PetasosQueryCityName = findViewById(R.id.QueryCityTitle);
        SharedPreferences HermesIO = getApplicationContext().getSharedPreferences("HERMES_PREFERENCES",0);
        PetasosQuery_City = HermesIO.getString("HermesQuery", "City Name");
        fetchingJSON();
        PetasosQueryCityName.setText(PetasosQuery_City);




        FloatingActionButton caduceusfab = findViewById(R.id.caduceusfab);


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
                singleItemModels.add(new SingleItemModel("Item " + j, "URL " + j,"","","",""));
            }
            dm.setAllItemInSection(singleItemModels);
            //allSampleData.add(dm);
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
                        Toast.makeText(PetasosActivity.this, "Learn more at www.emekaegwim.com", Toast.LENGTH_LONG).show();
                        //PetasosDetailsIntent();
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



    private void fetchingJSON() {

        showSimpleProgressDialog(this, "",getString(R.string.JSONloader_text),false);

        String petasosURLstring = "https://petasosapi.herokuapp.com/destination/" + PetasosQuery_City;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, petasosURLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            removeSimpleProgressDialog();

                            JSONObject obj = new JSONObject(response);



                                Time.setText(getString(R.string.time_ph) + obj.getString("time_url"));
                                Currency.setText(getString(R.string.currency_ph) + obj.getString("currency"));
                                Weather.setText(getString(R.string.climate_ph) + obj.getString("weather_url"));
                                CountryCode.setText(getString(R.string.itu_ph) + obj.getString("itu_countrycode"));
                                destBannerURL = obj.getString("bannerimg_url");

                                Picasso.get().load(destBannerURL).into(destBanner);


                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("places");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    PlaceDataModel placeModel = new PlaceDataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    placeModel.setPlacename(dataobj.getString("name"));
                                    placeModel.setWebsite_url(dataobj.getString("website_url"));
                                    placeModel.setPhone_number(dataobj.getString("phone_number"));
                                    placeModel.setAbouttext(dataobj.getString("abouttext"));
                                    placeModel.setBannerimg_url(dataobj.getString("bannerimg_url"));

                                    dataModelArrayList.add(placeModel);

                                }

                                setupRecycler();








                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        recyclerView.setVisibility(View.GONE);
                        PetasosErrorText.setVisibility(View.VISIBLE);
                        dividerlabel.setText(getString(R.string.error_ph));
                        dividerlabel.setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
                        Currency.setVisibility(View.INVISIBLE);
                        CountryCode.setVisibility(View.INVISIBLE);
                        Time.setVisibility(View.GONE);
                        Weather.setVisibility(View.GONE);

                       // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        removeSimpleProgressDialog();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void setupRecycler(){

        petasosRVAdapter = new PetasosRVAdapter(this,dataModelArrayList);
        recyclerView.setAdapter(petasosRVAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


    }

    public static void removeSimpleProgressDialog() {
        try {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(context, title, msg);
                progressDialog.setCancelable(isCancelable);
            }

            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








}
