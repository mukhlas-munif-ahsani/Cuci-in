package com.munifahsan.gowash.PesanReview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.munifahsan.gowash.PesanPilihCucian.adapter.FragmentAdapter;
import com.munifahsan.gowash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewCucianActivity extends AppCompatActivity {

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
}