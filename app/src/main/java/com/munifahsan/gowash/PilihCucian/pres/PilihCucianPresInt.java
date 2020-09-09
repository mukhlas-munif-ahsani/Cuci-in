package com.munifahsan.gowash.PilihCucian.pres;

public interface PilihCucianPresInt {
    void onCreate();
    void onDestroy();

    void createNewOrder(String idOrder, String idUser);
}
