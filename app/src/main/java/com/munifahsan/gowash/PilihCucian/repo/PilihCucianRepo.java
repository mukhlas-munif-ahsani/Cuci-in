package com.munifahsan.gowash.PilihCucian.repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.munifahsan.gowash.EventBuss.EventBus;
import com.munifahsan.gowash.EventBuss.GreenRobotEventBus;
import com.munifahsan.gowash.PilihCucian.PilihCucianEvent;

import java.util.HashMap;
import java.util.Map;

public class PilihCucianRepo implements PilihCucianRepoInt{

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference createOrderRef = firebaseFirestore.collection("ORDERS");

    public PilihCucianRepo() {
    }

    @Override
    public void creatNewOrder(String idOrder, String idUser){
        Map<String, Object> map = new HashMap<>();
        map.put("nUserId", idUser);
        Log.e("id order", ""+idOrder);
        createOrderRef.document(idOrder)
                .set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                postEvent(PilihCucianEvent.onSuccess);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                postEvent(PilihCucianEvent.onError, e.getMessage());
                Log.e("order error", e.getMessage());
            }
        });
    }

    public void postEvent(int type, String errorMessage){
        PilihCucianEvent mPilihCucian = new PilihCucianEvent();
        mPilihCucian.setEventType(type);

        if (errorMessage != null){
            mPilihCucian.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(mPilihCucian);
    }

    public void postEvent(int type){
        postEvent(type, null);
    }
}
