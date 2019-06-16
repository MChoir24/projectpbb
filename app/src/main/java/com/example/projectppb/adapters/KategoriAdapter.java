package com.example.projectppb.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectppb.activities.TaskActivity;
import com.example.projectppb.objects.DataHelper;
import com.example.projectppb.objects.Kategori;
import com.example.projectppb.R;

import java.util.ArrayList;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.MyViewHolder> {

    private ArrayList<Kategori> mDataset;
    private Context mContext;
    DataHelper dbcenter;

    public KategoriAdapter( Context mContext, ArrayList<Kategori> mDataset) {
        this.mDataset = mDataset;
        this.mContext = mContext;
        dbcenter = new DataHelper(mContext);
    }

    @NonNull
    @Override
    public KategoriAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_kategori, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_nama.setText(mDataset.get(i).getNama());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TaskActivity.class);
                intent.putExtra(TaskActivity.EXTRA_PERSON, mDataset.get(i));
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return (mDataset != null) ? mDataset.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nama;
        private CardView cardView;
        final KategoriAdapter kategoriAdapter ;

        public MyViewHolder(@NonNull View itemView, KategoriAdapter kategoriAdapter) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.namaKategori);
            cardView = itemView.findViewById(R.id.kategori_layout);
            this.kategoriAdapter = kategoriAdapter;
        }
    }
}
