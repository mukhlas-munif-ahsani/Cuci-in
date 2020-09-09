package com.munifahsan.gowash.Login.pres;

public interface LoginPresInt {
    void onCreate();

    void onDestroy();

    void validateLogin(String email, String pass);

    boolean isValidForm(String email, String pass);
}
