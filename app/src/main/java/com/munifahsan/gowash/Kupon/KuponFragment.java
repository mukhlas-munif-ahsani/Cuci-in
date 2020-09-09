package com.munifahsan.gowash.Kupon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.munifahsan.gowash.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KuponFragment extends Fragment {

    public KuponFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kupon, container, false);
    }
}
