package com.munifahsan.gowash.PesanPilihCucian.pres;

public interface PilihCucianPresInt {
    void onCreate();
    void onDestroy();

    void createNewOrder(String idOrder, String idUser);
}
