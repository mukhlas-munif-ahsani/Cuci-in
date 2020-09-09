package com.munifahsan.gowash.Login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.munifahsan.gowash.Login.pres.LoginPres;
import com.munifahsan.gowash.Login.pres.LoginPresInt;
import com.munifahsan.gowash.MainActivity;
import com.munifahsan.gowash.R;
import com.munifahsan.gowash.RegisterActivity;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginActivityInt{

    private LoginPresInt mLoginPres;

    @BindView(R.id.loginButton)
    Button mLoginBtn;
    @BindView(R.id.regBtn)
    MaterialButton mRegBtn;
    @BindView(R.id.editTextEmail)
    TextInputEditText mEmailEdt;
    @BindView(R.id.editTextPassword)
    TextInputEditText mPassEdt;
    @BindView(R.id.progressBar)
    ProgressBar mLoginLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mLoginPres = new LoginPres(this);
        mLoginPres.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPres.onDestroy();
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(){
        mLoginLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        mLoginLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setEmailError(String error){
        mEmailEdt.setError(error);
    }

    @Override
    public void setPassError(String error){
        mPassEdt.setError(error);
    }

    @OnClick(R.id.loginButton)
    public void loginOnClick(){
        String email = mEmailEdt.getText().toString();
        String pass = mPassEdt.getText().toString();

        if (mLoginPres.isValidForm(email, pass))
            mLoginPres.validateLogin(email, pass);
    }

    @Override
    public void navigateToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.regBtn)
    public void navigateToRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setInputsEnable(boolean enabled){
        mEmailEdt.setEnabled(enabled);
        mPassEdt.setEnabled(enabled);
        mLoginBtn.setEnabled(enabled);
        mRegBtn.setEnabled(enabled);
    }
}
