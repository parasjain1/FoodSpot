package com.starks.foodspots.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.starks.foodspots.R;
import com.starks.foodspots.viewholders.FoodSpotViewHolder;

import java.util.ArrayList;

/**
 * Created by monikapandey on 07/01/18.
 */

public class ImageViewsRecyclerViewAdapter extends RecyclerView.Adapter<ImageViewsRecyclerViewAdapter.ImageViewHolder> {

    ArrayList<Bitmap> images;
    Context context;
    LayoutInflater inflater;

    public ImageViewsRecyclerViewAdapter(Context context, ArrayList<Bitmap> images){
        this.images = images;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.image_view,parent,false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.imageView.setImageBitmap(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
