package com.munifahsan.gowash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import com.munifahsan.gowash.CardAdapter.ShadowTransformer;
import com.munifahsan.gowash.Home.LayananAdapter;
import com.munifahsan.gowash.Home.LayananModel;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    ViewPager viewPager;
    LayananAdapter mLayananAdapter;
    List<LayananModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private ShadowTransformer mShadowTransformer;
    ConstraintLayout mHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mHome = findViewById(R.id.home);
        viewPager = findViewById(R.id.viewPager);

        mLayananAdapter = new LayananAdapter();
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor1), "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor2), "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor3), "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor4), "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        mLayananAdapter.addCardItem(new LayananModel(getResources().getColor(R.color.cardColor5), "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));

        mShadowTransformer = new ShadowTransformer(viewPager, mLayananAdapter);
        viewPager.setAdapter(mLayananAdapter);
        viewPager.setPageTransformer(false, mShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPadding(130, 60, 130, 60);
        viewPager.setPageMargin(60);

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

                if (position < (mLayananAdapter.getCount() - 1) && position < (colors.length - 1)){
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
