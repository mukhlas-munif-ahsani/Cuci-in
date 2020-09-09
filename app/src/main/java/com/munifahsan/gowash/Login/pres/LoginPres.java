package com.munifahsan.gowash.Login.pres;

import com.munifahsan.gowash.EventBuss.EventBus;
import com.munifahsan.gowash.EventBuss.GreenRobotEventBus;
import com.munifahsan.gowash.Login.LoginEvent;
import com.munifahsan.gowash.Login.repo.LoginRepo;
import com.munifahsan.gowash.Login.repo.LoginRepoInt;
import com.munifahsan.gowash.Login.view.LoginActivityInt;

import org.greenrobot.eventbus.Subscribe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.munifahsan.gowash.Login.LoginEvent.onLoginError;
import static com.munifahsan.gowash.Login.LoginEvent.onLoginSuccess;

public class LoginPres implements LoginPresInt{

    private LoginRepoInt mLoginRepo;
    private LoginActivityInt mLoginActivity;
    private EventBus mEventBus;

    public LoginPres(LoginActivityInt mLoginActivity) {
        mLoginRepo = new LoginRepo();
        this.mLoginActivity = mLoginActivity;
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate(){
        mEventBus.register(this);
    }

    @Override
    public void onDestroy(){
        mLoginActivity = null;
        mEventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(LoginEvent event){
        switch (event.getEventType()){
            case onLoginSuccess:
                onLoginSuccess();
                break;
            case onLoginError:
                onLoginError(event.getErrorMessage());
                break;
        }
    }

    private void onLoginError(String errorMessage) {
        mLoginActivity.hideLoading();
        mLoginActivity.setInputsEnable(true);
        mLoginActivity.showMessage(errorMessage);
    }

    private void onLoginSuccess(){
        mLoginActivity.navigateToMain();
    }

    @Override
    public void validateLogin(String email, String pass){
        mLoginActivity.showLoading();
        mLoginActivity.setInputsEnable(false);

        mLoginRepo.login(email, pass);
    }

    @Override
    public boolean isValidForm(String email, String pass){
        boolean isValid = true;
        if (email.isEmpty()){
            isValid = false;
            mLoginActivity.setEmailError("E-mail Tidak Boleh Kosong");
        }

        if (!email.isEmpty() && !isEmailValid(email)){
            isValid = false;
            mLoginActivity.setEmailError("E-mail Tidak Valid");
        }

        if (pass.isEmpty()){
            isValid = false;
            mLoginActivity.setPassError("Password Tidak Boleh Kosong");
        }
        return isValid;
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
