package com.munifahsan.gowash.PilihCucian.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.munifahsan.gowash.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LainLainFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firebaseFirestore.collection("DAFTAR_BAJU").document("LAINLAIN").collection("DAFTAR_PAKAIAN");
    private FragmentAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    Query query;

    @BindView(R.id.listlain)
    RecyclerView mListLain;
    @BindView(R.id.lainListContent)
    RelativeLayout mLainListContent;
    @BindView(R.id.lainLoadingContent)
    ScrollView mLainLoadingContent;

    private static LainLainFragment instance;

    public LainLainFragment() {
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
        View view = inflater.inflate(R.layout.fragment_lain_lain, container, false);
        ButterKnife.bind(this, view);
        query = reference;

        FirestoreRecyclerOptions<FragmentModel> options = new FirestoreRecyclerOptions.Builder<FragmentModel>()
                .setQuery(query, FragmentModel.class)
                .build();

        adapter = new FragmentAdapter(options);
        mListLain.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mListLain.setLayoutManager(linearLayoutManager);
        mListLain.setAdapter(adapter);

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

    public static LainLainFragment getInstance(){
        return instance;
    }

    public void showContent(){
        mLainListContent.setVisibility(View.VISIBLE);
    }

    public void hideContent(){
        mLainListContent.setVisibility(View.INVISIBLE);
    }

    public void showLoading(){
        mLainLoadingContent.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        mLainLoadingContent.setVisibility(View.INVISIBLE);
    }
}