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

public class AnakFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firebaseFirestore.collection("DAFTAR_BAJU").document("ANAK").collection("DAFTAR_PAKAIAN");
    private FragmentAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    Query query;

    @BindView(R.id.listAnak)
    RecyclerView mListAnak;
    @BindView(R.id.anakListContent)
    RelativeLayout mAnakListContent;
    @BindView(R.id.anakLoadingContent)
    ScrollView mAnakLoadingContent;

    private static AnakFragment instance;

    public AnakFragment() {
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
        View view = inflater.inflate(R.layout.fragment_anak, container, false);
        ButterKnife.bind(this, view);

        query = reference;

        FirestoreRecyclerOptions<FragmentModel> options = new FirestoreRecyclerOptions.Builder<FragmentModel>()
                .setQuery(query, FragmentModel.class)
                .build();

        adapter = new FragmentAdapter(options);
        mListAnak.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mListAnak.setLayoutManager(linearLayoutManager);
        mListAnak.setAdapter(adapter);

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

    public static AnakFragment getInstance(){
        return instance;
    }

    public void showContent(){
        mAnakListContent.setVisibility(View.VISIBLE);
    }

    public void hideContent(){
        mAnakListContent.setVisibility(View.INVISIBLE);
    }

    public void showLoading(){
        mAnakLoadingContent.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        mAnakLoadingContent.setVisibility(View.INVISIBLE);
    }
}