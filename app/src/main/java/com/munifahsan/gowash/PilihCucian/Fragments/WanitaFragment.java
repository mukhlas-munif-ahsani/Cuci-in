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

public class WanitaFragment extends Fragment {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firebaseFirestore.collection("DAFTAR_BAJU").document("WANITA").collection("DAFTAR_PAKAIAN");
    private FragmentAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    Query query;

    @BindView(R.id.listWanita)
    RecyclerView mListWanita;
    @BindView(R.id.wanitaListContent)
    RelativeLayout mWanitaListContent;
    @BindView(R.id.wanitaLoadingContent)
    ScrollView mWanitaLoadingContent;

    private static WanitaFragment instance;

    public WanitaFragment() {
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
        View view = inflater.inflate(R.layout.fragment_wanita, container, false);
        ButterKnife.bind(this, view);
        query = reference;

        FirestoreRecyclerOptions<FragmentModel> options = new FirestoreRecyclerOptions.Builder<FragmentModel>()
                .setQuery(query, FragmentModel.class)
                .build();

        adapter = new FragmentAdapter(options);
        mListWanita.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mListWanita.setLayoutManager(linearLayoutManager);
        mListWanita.setAdapter(adapter);

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

    public static WanitaFragment getInstance(){
        return instance;
    }

    public void showContent(){
        mWanitaListContent.setVisibility(View.VISIBLE);
    }

    public void hideContent(){
        mWanitaListContent.setVisibility(View.INVISIBLE);
    }

    public void showLoading(){
        mWanitaLoadingContent.setVisibility(View.VISIBLE);
    }

    public void hideLoading(){
        mWanitaLoadingContent.setVisibility(View.INVISIBLE);
    }
}