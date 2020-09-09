package com.munifahsan.gowash.Login.view;

public interface LoginActivityInt {
    void showMessage(String message);

    void showLoading();

    void hideLoading();

    void setEmailError(String error);

    void setPassError(String error);

    void navigateToMain();

    void setInputsEnable(boolean enabled);
}
