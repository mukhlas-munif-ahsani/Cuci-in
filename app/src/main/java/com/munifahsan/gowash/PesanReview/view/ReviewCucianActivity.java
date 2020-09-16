package com.munifahsan.gowash.PesanReview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.munifahsan.gowash.PesanReview.ReviewAdapter;
import com.munifahsan.gowash.PesanReview.ReviewModel;
import com.munifahsan.gowash.PesanReview.pres.ReviewCucianPres;
import com.munifahsan.gowash.PesanReview.pres.ReviewCucianPresInt;
import com.munifahsan.gowash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewCucianActivity extends AppCompatActivity implements ReviewCucianActInt {

    private ReviewCucianPresInt mReviewCucianPres;

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firebaseFirestore.collection("ORDERS");
    private ReviewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    Query query;

    @BindView(R.id.reviewRv)
    RecyclerView mReviewRv;
    @BindView(R.id.reviewBeratTxt)
    TextView mBeratTxt;
    @BindView(R.id.subtotalHargaTxt)
    TextView mSubtotalHargaTxt;
    @BindView(R.id.diskonTxt)
    TextView mDiskon;
    @BindView(R.id.totalTxt)
    TextView mTotalHarga;

    private String mOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cucian);

        ButterKnife.bind(this);
        mReviewCucianPres = new ReviewCucianPres(this);
        mReviewCucianPres.onCreate();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mOrderId = (String) bundle.get("ORDER_ID");

        query = reference.document(mOrderId).collection("CUCIAN");

        FirestoreRecyclerOptions<ReviewModel> options = new FirestoreRecyclerOptions.Builder<ReviewModel>()
                .setQuery(query, ReviewModel.class)
                .build();
        adapter = new ReviewAdapter(options);
        mReviewRv.setHasFixedSize(true);
        mReviewRv.setNestedScrollingEnabled(false);
       //ViewCompat.setNestedScrollingEnabled(mReviewRv, false);
        linearLayoutManager = new LinearLayoutManager(this);
        mReviewRv.setLayoutManager(linearLayoutManager);
        mReviewRv.setAdapter(adapter);

        mReviewCucianPres.getData(mOrderId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReviewCucianPres.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void setTotalBerat(int totalBerat){
        mBeratTxt.setText(String.valueOf(totalBerat));
    }

    @Override
    public void setSubtotalHarga(int subtotalHarga){
        mSubtotalHargaTxt.setText(String.valueOf(subtotalHarga));
    }
}