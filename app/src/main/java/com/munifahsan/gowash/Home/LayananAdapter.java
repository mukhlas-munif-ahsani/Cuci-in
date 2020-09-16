package com.munifahsan.gowash.Home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.munifahsan.gowash.CardAdapter.CardAdapter;
import com.munifahsan.gowash.PesanPilihCucian.view.PilihCucianActivity;
import com.munifahsan.gowash.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class LayananAdapter extends PagerAdapter implements CardAdapter {

    private LayoutInflater layoutInflater;
    private List<CardView> mViews;
    private List<LayananModel> mData;
    private float mBaseElevation;

    public LayananAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(LayananModel item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(container.getContext());
        View view = layoutInflater.inflate(R.layout.item_layanan, container, false);
        container.addView(view);
        bind(mData.get(position), view);

        CardView mCardView = view.findViewById(R.id.cardBG);

        if (mBaseElevation == 0) {
            mBaseElevation = mCardView.getCardElevation();
        }

        final int page = position;

        Button mPilihLayananBtn = view.findViewById(R.id.pilihLayananBtn);

        mCardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, mCardView);
        mPilihLayananBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                send number of order
                 */
//                if (page == 0) {
                    navigateToPilihanActivity(container, page);
//                } else if (page == 1) {
//                    navigateToPilihanActivity(container, page);
//                } else if (page == 2) {
//                    navigateToPilihanActivity(container, page);
//                } else if (page == 3) {
//                    navigateToPilihanActivity(container, page);
//                } else if (page == 4) {
//                    navigateToPilihanActivity(container, page);
//                } else if (page == 5) {
//                    navigateToPilihanActivity(container, page);
//                }
            }
        });
        //container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(LayananModel item, View view) {

        TextView mTitle, mJenis, mItem1, mItem2, mItem3, mItem4, mItem5, mItem6, mJangka, mBerat;
        CardView mCard;
        Button mBtn;

        mBtn = view.findViewById(R.id.pilihLayananBtn);
        mCard = view.findViewById(R.id.cardBG);
        mTitle = view.findViewById(R.id.title);
        mJenis = view.findViewById(R.id.jenis);
        mItem1  = view.findViewById(R.id.txtItem1);
        mItem2 = view.findViewById(R.id.txtItem2);
        mItem3 = view.findViewById(R.id.txtItem3);
        mItem4 = view.findViewById(R.id.txtItem4);
        mItem5 = view.findViewById(R.id.txtItem5);
        mItem6 = view.findViewById(R.id.txtItem6);
        mJangka = view.findViewById(R.id.jangka);
        mBerat = view.findViewById(R.id.berat);

        mCard.setCardBackgroundColor(item.getColor());
        mBtn.setTextColor(item.getColor());
        mTitle.setText(item.getTitle());
        mJenis.setText(item.getJenis());
        mItem1.setText(item.getItem1());
        mItem2.setText(item.getItem2());
        mItem3.setText(item.getItem3());
        mItem4.setText(item.getItem4());
        mItem5.setText(item.getItem5());
        mItem6.setText(item.getItem6());
        mJangka.setText(item.getJangka());
        mBerat.setText(item.getBerat());

        if (item.getJenis()==null){
            mTitle.setTextSize(35);
            mJenis.setVisibility(View.GONE);
            //mItem1.setPadding(0, 0, 0, 0);
        }

        if (item.getItem1() == null){
            mItem1.setVisibility(View.GONE);
        }

        if (item.getItem2() == null){
            mItem2.setVisibility(View.GONE);
        }

        if (item.getItem3() == null){
            mItem3.setVisibility(View.GONE);
        }

        if (item.getItem4() == null){
            mItem4.setVisibility(View.GONE);
        }

        if (item.getItem5() == null){
            mItem5.setVisibility(View.GONE);
        }

        if (item.getItem6() == null){
            mItem6.setVisibility(View.GONE);
        }

    }

    public void navigateToPilihanActivity(ViewGroup container, int jenis) {
        Intent intent = new Intent(container.getContext(), PilihCucianActivity.class);
        intent.putExtra("JENIS_LAYANAN", jenis);
        container.getContext().startActivity(intent);
    }
}
