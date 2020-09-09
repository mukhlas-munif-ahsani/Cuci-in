package com.munifahsan.gowash.Login.repo;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.munifahsan.gowash.EventBuss.EventBus;
import com.munifahsan.gowash.EventBuss.GreenRobotEventBus;
import com.munifahsan.gowash.Login.LoginEvent;

public class LoginRepo implements LoginRepoInt{

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private String mUserId;
    private FirebaseUser currentUser;
    private String level;

    public LoginRepo() {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void login(String email, String pass){
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mUserId = auth.getCurrentUser().getUid();
                    postEvent(LoginEvent.onLoginSuccess);
                } else {
                    String error = task.getException().getMessage();
                    postEvent(LoginEvent.onLoginError, error);
                }
            }
        });
    }

    private void postEvent(int type, String errorMessage, String level) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);

        if (level != null){
            loginEvent.setLevel(level);
        }

        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null, null);
    }

    private void postEvent(int type, String level) {
        postEvent(type, null, level);
    }
}
