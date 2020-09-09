package com.munifahsan.gowash.Home;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.munifahsan.gowash.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PromoAdapter extends SliderViewAdapter<PromoAdapter.SliderAdapterVH> {

    private List<CardView> mViews;
    private List<PromoModel> mData;

    public PromoAdapter() {
        this.mViews = new ArrayList<>();
        this.mData = new ArrayList<>();
    }

    public void addCardItem(PromoModel item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promo, null);
        return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.textViewDescription.setText(mData.get(position).getDesc());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
        }
    }
}
