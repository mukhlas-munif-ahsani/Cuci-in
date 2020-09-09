package com.munifahsan.gowash.PilihCucian.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.munifahsan.gowash.CustomViewPager;
import com.munifahsan.gowash.IdGenerator.IdGenerator;
import com.munifahsan.gowash.PilihCucian.Anak.AnakFragment;
import com.munifahsan.gowash.PilihCucian.LainLain.LainLainFragment;
import com.munifahsan.gowash.PilihCucian.Pria.PriaFragment;
import com.munifahsan.gowash.PilihCucian.Wanita.WanitaFragment;
import com.munifahsan.gowash.PilihCucian.pres.PilihCucianPres;
import com.munifahsan.gowash.PilihCucian.pres.PilihCucianPresInt;
import com.munifahsan.gowash.R;
import com.munifahsan.gowash.IdGenerator.IdGeneratorInt;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PilihCucianActivity extends AppCompatActivity implements PilihCucianActivityInt {

    private PilihCucianPresInt mPilihCucianPres;

    @BindView(R.id.viewpager_pilih_baju)
    CustomViewPager mViewPager;
    @BindView(R.id.viewpagertab_daftar_pilihan)
    SmartTabLayout mPilihanTablayout;
    @BindView(R.id.Toolbar)
    Toolbar mToolbar;

    @BindView(R.id.totalBaju)
    TextView mTotalBajuTxt;
    @BindView(R.id.totalBerat)
    TextView mTotalBeratTxt;
    @BindView(R.id.totalHarga)
    TextView mTotalHargaTxt;

    private int mTotalBaju = 0;
    private int mTotalBerat = 0;
    private int mTotalHarga = 0;

    private IdGeneratorInt mGeneratedId;
    private String mOrderId;
    private String mUserId;

    private static PilihCucianActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_cucian);

        ButterKnife.bind(this);
        mPilihCucianPres = new PilihCucianPres(this);
        mPilihCucianPres.onCreate();
        mGeneratedId = IdGenerator.getInstance();
        instance = this;

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("PRIA", PriaFragment.class)
                .add("WANITA", WanitaFragment.class)
                .add("ANAK", AnakFragment.class)
                .add("LAIN-LAIN", LainLainFragment.class)
                .create()
        );

        int jenis = getIntent().getIntExtra("JENIS_LAYANAN", 0);
        Toast.makeText(this, "LAYANAN : " + jenis, Toast.LENGTH_LONG).show();
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mPilihanTablayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mViewPager.setAdapter(adapter);
        mPilihanTablayout.setViewPager(mViewPager);

        createNewOrder();
//        showMessage(mGeneratedId.getOrderId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPilihCucianPres.onDestroy();
    }

    public static PilihCucianActivity getInstance() {
        return instance;
    }

    public void createNewOrder() {
        mOrderId = mGeneratedId.getOrderId();
        mUserId = mGeneratedId.getUserId();

        mPilihCucianPres.createNewOrder(mOrderId, mUserId);
    }

    public void tambahTotalhBaju() {
        mTotalBaju = mTotalBaju + 1;
        mTotalBajuTxt.setText(String.valueOf(mTotalBaju));
    }

    public void kurangTotalBaju() {
        mTotalBaju = mTotalBaju - 1;
        if (mTotalBaju < 1)
            mTotalBaju = 0;
        mTotalBajuTxt.setText(String.valueOf(mTotalBaju));
    }

    public void tambahTotalHarga(int harga) {
        mTotalHarga = mTotalHarga + harga;
        DecimalFormat decimalFormat = new DecimalFormat("#,##,###,###");
        mTotalHargaTxt.setText(decimalFormat.format(mTotalHarga));
    }

    public void kurangTotalHarga(int harga) {
        mTotalHarga = mTotalHarga - harga;
        DecimalFormat decimalFormat = new DecimalFormat("#,##,###,###");
        mTotalHargaTxt.setText(decimalFormat.format(mTotalHarga));
    }

    public void tambahTotalBerat(int berat) {
        mTotalBerat = mTotalBerat + berat;
        DecimalFormat decimalFormat = new DecimalFormat("#,##,###,###");
        mTotalBeratTxt.setText(decimalFormat.format(mTotalBerat));
    }

    public void kurangTotalBerat(int berat) {
        mTotalBerat = mTotalBerat - berat;
        DecimalFormat decimalFormat = new DecimalFormat("#,##,###,###");
        mTotalBeratTxt.setText(decimalFormat.format(mTotalBerat));
    }

    @Override
    public void showBajuList() {
        PriaFragment.getInstance().showContent();
    }

    @Override
    public void hideBajuList() {
        PriaFragment.getInstance().hideContent();
    }

    @Override
    public void showBajuListLoading() {
        PriaFragment.getInstance().showLoading();
    }

    @Override
    public void hideBajuListLoading() {
        PriaFragment.getInstance().hideLoading();
    }

    public String orderId() {
        return mOrderId;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}