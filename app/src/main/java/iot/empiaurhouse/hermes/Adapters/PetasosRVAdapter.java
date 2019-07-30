package iot.empiaurhouse.hermes.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import iot.empiaurhouse.hermes.Model.PlaceDataModel;
import iot.empiaurhouse.hermes.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class PetasosRVAdapter extends RecyclerView.Adapter<PetasosRVAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<PlaceDataModel> dataModelArrayList;

    public PetasosRVAdapter(Context ctx, ArrayList<PlaceDataModel> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public PetasosRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.petasos_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(PetasosRVAdapter.MyViewHolder holder, int position) {

        Picasso.get().load(dataModelArrayList.get(position).getBannerimg_url()).into(holder.iv);
        holder.placename.setText(dataModelArrayList.get(position).getPlacename());
        holder.website_url.setText(dataModelArrayList.get(position).getWebsite_url());
        holder.phone_url.setText(dataModelArrayList.get(position).getPhone_number());
        holder.abouttext.setText(dataModelArrayList.get(position).getAbouttext());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView placename, website_url, phone_url, abouttext;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            placename = (TextView) itemView.findViewById(R.id.place_name);
            website_url = (TextView) itemView.findViewById(R.id.website_url);
            phone_url = (TextView) itemView.findViewById(R.id.phone_url);
            abouttext = (TextView) itemView.findViewById(R.id.about_text);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

    }
}