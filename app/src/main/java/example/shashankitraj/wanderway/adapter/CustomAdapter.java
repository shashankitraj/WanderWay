package example.shashankitraj.wanderway.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import example.shashankitraj.wanderway.R;
import example.shashankitraj.wanderway.model.CityDataModel;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private ArrayList<CityDataModel> dataSet;
    private int lastPosition = -1;
    private Context context;

    public CustomAdapter(ArrayList<CityDataModel> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView tvName = holder.tvTitle;
        ImageView img = holder.imgIcon;

        tvName.setText(dataSet.get(position).getTitle().toString());
        img.setImageResource(dataSet.get(position).getImage());

        // setAnimation(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView imgIcon;

        public MyViewHolder(View itemView) {
            super(itemView);

            //initializing of views
            this.tvTitle=itemView.findViewById(R.id.cardText);
            this.imgIcon=itemView.findViewById(R.id.cardImage);
        }
    }
}


