package com.example.proftaak1_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {


    private Context context;
    private List<AttractionInformation> allAtractions;
    private onItemClickListener clickListener;



    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;

        public Holder(@NonNull View itemView, Adapter adapter) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.cardview_textView);
            imageView = (ImageView) itemView.findViewById(R.id.cardview_imageview);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPosition = getAdapterPosition();
            clickListener.onItemClick(clickPosition);
        }
    }

    public Adapter(Context context, List<AttractionInformation> allAtractions, onItemClickListener clickListener) {
        this.context = context;
        this.allAtractions = allAtractions;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
        Holder viewHolder = new Holder(itemView, this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        AttractionInformation information = this.allAtractions.get(position);

        holder.textView.setText(information.getCaption());

        switch (information.getTypeOfAttraction()){
            case "cobra":
                holder.imageView.setImageResource(R.drawable.cobra);
                break;
            case "fabelwoud":
                holder.imageView.setImageResource(R.drawable.the_tree);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return allAtractions.size();
    }


}


