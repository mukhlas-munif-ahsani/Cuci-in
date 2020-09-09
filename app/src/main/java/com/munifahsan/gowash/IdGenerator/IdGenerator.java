package com.munifahsan.gowash.IdGenerator;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IdGenerator implements IdGeneratorInt {
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String mTanggal;
    private String mBulan;
    private String mTahun;
    private String mJam;

    private FirebaseAuth mAuth;
    private String mCurrentId;

    private static class SingletonHolder{
        private static final IdGenerator INSTANCE = new IdGenerator();
    }

    public static IdGenerator getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public IdGenerator(){
        mAuth = FirebaseAuth.getInstance();
        mCurrentId = mAuth.getCurrentUser().getUid();
    }

    @Override
    public String getOrderId(){
        getjam();
        getTangal();
        getBulan();
        getTahun();

        return mCurrentId+mJam+mTanggal+mBulan+mTahun;
    }

    @Override
    public String getUserId(){
        return mCurrentId;
    }

    public void getTangal() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("d");
        mTanggal = dateFormat.format(calendar.getTime());
    }

    public void getBulan() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("M");
        mBulan = dateFormat.format(calendar.getTime());
    }

    public void getTahun() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy");
        mTahun = dateFormat.format(calendar.getTime());
    }

    public void getjam() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("hhmmss");
        mJam = dateFormat.format(calendar.getTime());
    }
}
