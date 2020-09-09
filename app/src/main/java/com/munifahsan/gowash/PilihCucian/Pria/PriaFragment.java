package com.munifahsan.gowash.PilihCucian.Pria;

import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.facebook.shimmer.Shimmer;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.munifahsan.gowash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriaFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference atasanRef = firebaseFirestore.collection("DAFTAR_BAJU").document("PRIA").collection("DAFTAR_PAKAIAN");
    private PriaAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    Query query;

    @BindView(R.id.listAtasan)
    RecyclerView mListAtasan;
    @BindView(R.id.priaListContent)
    RelativeLayout mPriaListContent;
    @BindView(R.id.priaLoadingContent)
    ScrollView mPriaLoadingContent;

    private static PriaFragment instance;

    public PriaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pria, container, false);

        ButterKnife.bind(this, view);

        query = atasanRef;

        FirestoreRecyclerOptions<PriaModel> options = new FirestoreRecyclerOptions.Builder<PriaModel>()
                .setQuery(query, PriaModel.class)
                .build();

        adapter = new PriaAdapter(options);
        mListAtasan.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //mListAtasan.setNestedScrollingEnabled(false);
        mListAtasan.setLayoutManager(linearLayoutManager);
        mListAtasan.setAdapter(adapter);
       // ViewCompat.setNestedScrollingEnabled(mListAtasan, false);

        instance = this;

        return view;
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

    public static PriaFragment getInstance(){
        return instance;
    }

    public void showContent(){
        mPriaListContent.setVisibility(View.VISIBLE);

    }

    public void hideContent(){
        mPriaListContent.setVisibility(View.INVISIBLE);
    }

    public void showLoading(){
        mPriaLoadingContent.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        mPriaLoadingContent.setVisibility(View.INVISIBLE);
    }

    public String idOrder(){
        String id = "sdfasf";
        return id;
    }
}