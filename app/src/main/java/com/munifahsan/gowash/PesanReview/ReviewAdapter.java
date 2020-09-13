package com.munifahsan.gowash.PesanReview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.munifahsan.gowash.R;

import java.text.DecimalFormat;

public class ReviewAdapter extends FirestoreRecyclerAdapter<ReviewModel, ReviewAdapter.Holder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReviewAdapter(@NonNull FirestoreRecyclerOptions<ReviewModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ReviewAdapter.Holder holder, int position, @NonNull ReviewModel model) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##,###,###");
        holder.mNama.setText(model.getnNama());
        int berat = model.getnBerat() * model.getnJumlah();
        int harga = model.getnHarga() * model.getnJumlah();
        holder.mBerat.setText(decimalFormat.format(berat));
        holder.mHarga.setText(decimalFormat.format(harga));
        holder.mJumlah.setText(String.valueOf(model.getnJumlah()));
    }

    @NonNull
    @Override
    public ReviewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_pesanan, parent, false);
        return new Holder(view);
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView mJumlah;
        private TextView mNama;
        private TextView mBerat;
        private TextView mHarga;

        public Holder(@NonNull View itemView) {
            super(itemView);

            mJumlah = itemView.findViewById(R.id.reviewJumlahTxt);
            mNama = itemView.findViewById(R.id.reviewNamaTxt);
            mBerat = itemView.findViewById(R.id.reviewBeratTxt);
            mHarga = itemView.findViewById(R.id.reviewHargaTxt);
        }
    }
}
