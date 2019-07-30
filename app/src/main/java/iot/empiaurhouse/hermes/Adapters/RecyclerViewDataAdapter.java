package iot.empiaurhouse.hermes.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import iot.empiaurhouse.hermes.Model.Places;
import iot.empiaurhouse.hermes.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder>{

    private List<Places> dataList;

    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public RecyclerViewDataAdapter(List<Places> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.petasoslist_item, null);
        ItemRowHolder rowHolder = new ItemRowHolder(v);
        return rowHolder;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {
        holder.itemTitle.setText(dataList.get(position).getPlacename());

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(mContext));
        builder.build().load(dataList.get(position).getBannerimg_url())
                .placeholder((R.drawable.florencedome))
                .error(R.drawable.abodeiconb)
                .into(holder.thumbImg);


        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        holder.recyclerView.setRecycledViewPool(recycledViewPool);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Button More Clicked!" + sectionName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        protected TextView itemTitle;
        protected RecyclerView recyclerView;
        protected Button btnMore;
        protected ImageView thumbImg;

        public ItemRowHolder(View itemView) {
            super(itemView);
            this.itemTitle = itemView.findViewById(R.id.itemTitle);
            this.recyclerView = itemView.findViewById(R.id.recycler_view_list);
            this.btnMore = itemView.findViewById(R.id.btnMore);
            this.thumbImg = itemView.findViewById(R.id.itemImage);
        }
    }
}
