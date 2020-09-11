package com.munifahsan.gowash.PesanPilihCucian.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.munifahsan.gowash.PesanPilihCucian.model.BajuModel;
import com.munifahsan.gowash.PesanPilihCucian.model.FragmentModel;
import com.munifahsan.gowash.PesanPilihCucian.view.PilihCucianActivity;
import com.munifahsan.gowash.R;

import java.util.HashMap;
import java.util.Map;

import at.markushi.ui.CircleButton;

public class FragmentAdapter extends FirestoreRecyclerAdapter<FragmentModel, FragmentAdapter.Holder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FragmentAdapter(@NonNull FirestoreRecyclerOptions<FragmentModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull FragmentModel model) {
        holder.mNamaBaju.setText(model.getnNama());
        holder.mBeratbaju.setText(String.valueOf(model.getnBerat()));
        holder.mHargabaju.setText(String.valueOf(model.getnHarga()));
        holder.mJumlahbaju.setText(String.valueOf(holder.jumlahBaju));
        holder.mBajuCloudLoading.setVisibility(View.INVISIBLE);
        holder.mCloudImg.setVisibility(View.INVISIBLE);

        String id = PilihCucianActivity.getInstance().orderId();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        if (model.getnTipe() == 1) {
            holder.mLabel.setVisibility(View.VISIBLE);
            holder.mCard.setVisibility(View.GONE);
            holder.mLabel.setText(model.getnNama());
        } else {
            holder.mLabel.setVisibility(View.GONE);
            holder.mCard.setVisibility(View.VISIBLE);
            //get---
            firebaseFirestore.collection("ORDERS").document(id)
                    .collection("CUCIAN").document(model.getId())
                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        BajuModel model = documentSnapshot.toObject(BajuModel.class);
                        holder.mJumlahbaju.setText(String.valueOf(model.getnJumlah()));
                        holder.jumlahBaju = model.getnJumlah();
                        holder.mBajuCloudLoading.setVisibility(View.INVISIBLE);
                        holder.mCloudImg.setVisibility(View.VISIBLE);

                        if (model.getnJumlah() == 0) {
                            holder.mCloudImg.setVisibility(View.INVISIBLE);
                            holder.mKurangBaju.setEnabled(false);
                        }
                    }
                }
            });
            //get---

            holder.mTambahbaju.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.jumlahBaju = holder.jumlahBaju + 1;
                    holder.mJumlahbaju.setText(String.valueOf(holder.jumlahBaju));

                    holder.mBajuCloudLoading.setVisibility(View.VISIBLE);
                    holder.mCloudImg.setVisibility(View.INVISIBLE);

                    Map<String, Object> map = new HashMap<>();
                    map.put("nJumlah", holder.jumlahBaju);
                    map.put("nHarga", model.getnHarga());
                    map.put("nBerat", model.getnBerat());
                    map.put("nNama", model.getnNama());

                    //set---
                    firebaseFirestore.collection("ORDERS").document(id)
                            .collection("CUCIAN").document(model.getId())
                            .set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //get---
                                firebaseFirestore.collection("ORDERS").document(id)
                                        .collection("CUCIAN").document(model.getId())
                                        .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()) {
                                            BajuModel model = documentSnapshot.toObject(BajuModel.class);
                                            holder.mJumlahbaju.setText(String.valueOf(model.getnJumlah()));
                                            holder.mBajuCloudLoading.setVisibility(View.INVISIBLE);
                                            holder.mCloudImg.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                                //get---
                            }
                        }
                    });
                    //set---

                    holder.mKurangBaju.setEnabled(true);

                    PilihCucianActivity.getInstance().tambahTotalhBaju();
                    PilihCucianActivity.getInstance().tambahTotalHarga(model.getnHarga());
                    PilihCucianActivity.getInstance().tambahTotalBerat(model.getnBerat());
                }
            });

            holder.mKurangBaju.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.jumlahBaju = holder.jumlahBaju - 1;

                    if (holder.jumlahBaju < 1) {
                        holder.jumlahBaju = 0;
                    }

                    /*kill the button when baju below 0*/
                    if (holder.jumlahBaju == 0) {
                        holder.mKurangBaju.setEnabled(false);
                    } else {
                        holder.mKurangBaju.setEnabled(true);
                    }

                    holder.mJumlahbaju.setText(String.valueOf(holder.jumlahBaju));
                    holder.mBajuCloudLoading.setVisibility(View.VISIBLE);
                    holder.mCloudImg.setVisibility(View.INVISIBLE);

                    Map<String, Object> map = new HashMap<>();
                    map.put("nJumlah", holder.jumlahBaju);
                    map.put("nHarga", model.getnHarga());
                    map.put("nBerat", model.getnBerat());
                    map.put("nNama", model.getnNama());

                    //set---
                    firebaseFirestore.collection("ORDERS").document(id)
                            .collection("CUCIAN").document(model.getId())
                            .set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //get---
                                firebaseFirestore.collection("ORDERS").document(id)
                                        .collection("CUCIAN").document(model.getId())
                                        .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()) {
                                            BajuModel model = documentSnapshot.toObject(BajuModel.class);
                                            holder.mJumlahbaju.setText(String.valueOf(model.getnJumlah()));
                                            holder.mBajuCloudLoading.setVisibility(View.INVISIBLE);
                                            holder.mCloudImg.setVisibility(View.VISIBLE);

                                            if (model.getnJumlah() == 0) {
                                                holder.mCloudImg.setVisibility(View.INVISIBLE);
                                                //delete---
                                                firebaseFirestore.collection("ORDERS").document(id)
                                                        .collection("CUCIAN").document(model.getId())
                                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        //do something
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        //do something
                                                    }
                                                });
                                                //delete---
                                            }
                                        }
                                    }
                                });
                                //get---
                            }
                        }
                    });
                    //set---

                    PilihCucianActivity.getInstance().kurangTotalBaju();
                    PilihCucianActivity.getInstance().kurangTotalHarga(model.getnHarga());
                    PilihCucianActivity.getInstance().kurangTotalBerat(model.getnBerat());
                }
            });
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baju, parent, false);
        return new Holder(view);
    }

    public class Holder extends RecyclerView.ViewHolder {

        private int jumlahBaju = 0;

        private CircleButton mTambahbaju;
        private CircleButton mKurangBaju;
        private TextView mHargabaju;
        private TextView mBeratbaju;
        private TextView mNamaBaju;
        private TextView mJumlahbaju;
        private ImageView mGambarBaju;
        private ProgressBar mBajuCloudLoading;
        private ImageView mCloudImg;
        private TextView mLabel;
        private CardView mCard;

        public Holder(@NonNull View itemView) {
            super(itemView);

            mTambahbaju = itemView.findViewById(R.id.tambahBaju);
            mKurangBaju = itemView.findViewById(R.id.kurangBaju);
            mNamaBaju = itemView.findViewById(R.id.namaBaju);
            mBeratbaju = itemView.findViewById(R.id.beratBaju);
            mHargabaju = itemView.findViewById(R.id.hargaBaju);
            mGambarBaju = itemView.findViewById(R.id.gambarBaju);
            mJumlahbaju = itemView.findViewById(R.id.jumlahBaju);
            mBajuCloudLoading = itemView.findViewById(R.id.cloudLoading);
            mCloudImg = itemView.findViewById(R.id.imgCloud);
            mLabel = itemView.findViewById(R.id.labelTxt);
            mCard = itemView.findViewById(R.id.cardCrd);

        }
    }

    public void showLog(String msg) {
        Log.d("pesan : ", msg);
    }

}
