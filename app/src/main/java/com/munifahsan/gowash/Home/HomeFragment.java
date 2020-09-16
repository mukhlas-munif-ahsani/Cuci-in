package com.munifahsan.gowash.Home;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.munifahsan.gowash.CardAdapter.ShadowTransformer;
import com.munifahsan.gowash.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private ViewPager viewPager;
    private SliderView mSliderView;
    private LayananAdapter mLayananAdapter;
    private PromoAdapter mPromoAdapter;
    private List<LayananModel> models;
    private Integer[] colors = null;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private ShadowTransformer mShadowTransformer;
    private FrameLayout mHome;
    private Runnable runnable;

    @BindView(R.id.pilihPaketTxt)
    TextView mPilihPaktetTxt;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        mPilihPaktetTxt.setTextColor(getResources().getColor(R.color.putih));

        mHome = view.findViewById(R.id.home);
        viewPager = view.findViewById(R.id.viewPager);
        mSliderView = view.findViewById(R.id.imageSlider);

        mLayananAdapter = new LayananAdapter();

        /*
          paket 1
         */
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor1),
                "Cuci Komplit",
                "KILAT",
                "Hemat",
                "Cuci kering",
                "Strika uap",
                "Pewangi pakaian",
                "Antar & jempu",
                null,
                "3 Hari",
                "3,5K/kg"));
        /*
          paket 2
         */
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor1),
                "Cuci Komplit",
                null,
                "Cuci kering",
                "Strika uap",
                "Pewangi pakaian",
                "Antar & jempu",
                null,
                null,
                "3 Hari",
                "3,5K/kg"));
        /*
          paket 3
         */
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor1),
                "Cuci Komplit",
                null,
                "Cuci kering",
                "Strika uap",
                null,
                null,
                null,
                null,
                "3 Hari",
                "3,5K/kg"));
        /*
          paket 4
         */
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor1),
                "Cuci Komplit",
                null,
                "Cuci kering",
                "Strika uap",
                "Pewangi pakaian",
                null,
                null,
                null,
                "3 Hari",
                "3,5K/kg"));
        /*
          paket 5
         */
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor1),
                "Cuci Komplit",
                "KILAT",
                "Cuci kering",
                "Strika uap",
                "Pewangi pakaian",
                null,
                null,
                null,
                "3 Hari",
                "3,5K/kg"));

        mShadowTransformer = new ShadowTransformer(viewPager, mLayananAdapter);

        setlayananItem();

        mPromoAdapter = new PromoAdapter();
        mPromoAdapter.addCardItem(new PromoModel("coba coba coba"));
        mPromoAdapter.addCardItem(new PromoModel("coba coba coba"));
        mPromoAdapter.addCardItem(new PromoModel("coba coba coba"));
        mPromoAdapter.addCardItem(new PromoModel("coba coba coba"));
        mPromoAdapter.addCardItem(new PromoModel("coba coba coba"));

        mSliderView.setSliderAdapter(mPromoAdapter);
        mSliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        mSliderView.setIndicatorSelectedColor(Color.WHITE);
        mSliderView.setIndicatorUnselectedColor(Color.GRAY);
        mSliderView.setScrollTimeInSec(3);
        mSliderView.setAutoCycle(true);
        mSliderView.startAutoCycle();

        return view;
    }


    public void setlayananItem() {

        mShadowTransformer.enableScaling(true);
        viewPager.setAdapter(mLayananAdapter);
        viewPager.setPageTransformer(false, mShadowTransformer);
        viewPager.setOffscreenPageLimit(3);

        Integer[] colors_temp = {
                getResources().getColor(R.color.backGroundColor1),
                getResources().getColor(R.color.backGroundColor2),
                getResources().getColor(R.color.backGroundColor3),
                getResources().getColor(R.color.backGroundColor4),
                getResources().getColor(R.color.backGroundColor5),
        };

        colors = colors_temp;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (mLayananAdapter.getCount() - 1) && position < (colors.length - 1)) {
                    mHome.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    mHome.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
