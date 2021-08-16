package com.ardi.apppenyimpananktp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView fotoKtpImageView;
    TextView namaDetailTextView;
    TextView nikDetailTextView;
    TextView dateDetailTextView;

    public MyViewHolder(@NonNull View itemView){
        super(itemView);
    }
}
