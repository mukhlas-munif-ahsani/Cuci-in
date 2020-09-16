package com.munifahsan.gowash.PesanReview.repo;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.munifahsan.gowash.EventBuss.EventBus;
import com.munifahsan.gowash.EventBuss.GreenRobotEventBus;
import com.munifahsan.gowash.PesanReview.ReviewCucianEvent;
import com.munifahsan.gowash.PesanReview.ReviewModel;

public class ReviewCucianRepo implements ReviewCucianRepoInt {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firebaseFirestore.collection("ORDERS");

    public ReviewCucianRepo() {
    }

    @Override
    public void getData(String orderId) {
        reference.document(orderId)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ReviewModel model = documentSnapshot.toObject(ReviewModel.class);
                    postEvent(ReviewCucianEvent.onSuccess, null, model.getnTotalBerat(), model.getnTotalHarga());
                }
            }
        });
    }

    public void postEvent(int type, String errorMessage, int totalBerat, int totalHarga) {
        ReviewCucianEvent mReviewCucian = new ReviewCucianEvent();
        mReviewCucian.setEventType(type);

        if (errorMessage != null) {
            mReviewCucian.setErrorMessage(errorMessage);
        }

        if (totalBerat != 0) {
            mReviewCucian.setTotalBerat(totalBerat);
        }

        if (totalHarga != 0) {
            mReviewCucian.setTotalHarga(totalHarga);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(mReviewCucian);
    }

    public void postEvent(int type) {
        postEvent(type, null, 0, 0);
    }
}
