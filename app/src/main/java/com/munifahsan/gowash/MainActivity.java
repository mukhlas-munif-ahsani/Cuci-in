package com.munifahsan.gowash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.munifahsan.gowash.History.HistoryFragment;
import com.munifahsan.gowash.Home.HomeFragment;
import com.munifahsan.gowash.Kupon.KuponFragment;
import com.munifahsan.gowash.Profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.circle_bot_nav_home_active)
    MaterialButton mCircleNavHomeActive;
    @BindView(R.id.circle_bot_nav_home_inactive)
    MaterialButton mCircleNavHomeInactive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        Intent intent = new Intent(this, TestActivity.class);
        //startActivity(intent);

        getFragmentPage(new HomeFragment());
        mBottomNavigationView.getMenu().findItem(R.id.itemEmpty).setChecked(true);

        showKuponNotif(true);

    }

    public void showKuponNotif(Boolean active) {
        if (active) {
            mBottomNavigationView.getMenu().findItem(R.id.itemKupon).setIcon(getResources().getDrawable(R.drawable.ic_nav_promo_inactive_notif));
        }
    }

    public void showHistoryNotif(Boolean active){
        if (active) {
            mBottomNavigationView.getMenu().findItem(R.id.itemHistory).setIcon(getResources().getDrawable(R.drawable.ic_nav_promo_inactive_notif));
        }
    }

    @OnClick(R.id.circle_bot_nav_home_inactive)
    public void circleBotNavHomeInactiveOnClick() {
        showKuponNotif(true);
        mCircleNavHomeActive.setVisibility(View.VISIBLE);
        mCircleNavHomeInactive.setVisibility(View.INVISIBLE);
        getFragmentPage(new HomeFragment());
        mBottomNavigationView.getMenu().findItem(R.id.itemEmpty).setChecked(true);
    }

    private void getFragmentPage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }
    }

    // method untuk load fragment yang sesuai
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.itemKetentuan:
                //fragment = new HistoryFragment();
                break;
            case R.id.itemHistory:
                fragment = new HistoryFragment();
                mCircleNavHomeActive.setVisibility(View.INVISIBLE);
                mCircleNavHomeInactive.setVisibility(View.VISIBLE);
                break;
            case R.id.itemKupon:
                fragment = new KuponFragment();
                mBottomNavigationView.getMenu().findItem(R.id.itemKupon).setIcon(getResources().getDrawable(R.drawable.state_bot_nave_ic_promo));
                mCircleNavHomeActive.setVisibility(View.INVISIBLE);
                mCircleNavHomeInactive.setVisibility(View.VISIBLE);
                break;
            case R.id.itemProfile:
                fragment = new ProfileFragment();
                mCircleNavHomeActive.setVisibility(View.INVISIBLE);
                mCircleNavHomeInactive.setVisibility(View.VISIBLE);
                break;
        }
        return loadFragment(fragment);
    }
}
