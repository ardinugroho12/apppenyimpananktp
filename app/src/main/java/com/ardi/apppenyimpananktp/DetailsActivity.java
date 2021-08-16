package com.ardi.apppenyimpananktp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailsActivity extends AppCompatActivity {

    TextView namaDetailTextView,nikDetailTextView,dateDetailTextView;
    ImageView fotoKtpImageView;

    private void initializeWidgets(){
        namaDetailTextView= findViewById(R.id.namaDetailTextView);
        nikDetailTextView= findViewById(R.id.nikDetailTextView);
        dateDetailTextView= findViewById(R.id.dateDetailTextView);
        fotoKtpImageView=findViewById(R.id.fotoKtpImageView);
    }
    private String getDateToday(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        String today= dateFormat.format(date);
        return today;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeWidgets();

        //RECEIVE DATA FROM ITEMSACTIVITY VIA INTENT
        Intent i=this.getIntent();
        String nama=i.getExtras().getString("NAMA_KEY");
        String nik=i.getExtras().getString("NIK_KEY");
        String imageURL=i.getExtras().getString("IMAGE_KEY");

        //SET RECEIVED DATA TO TEXTVIEWS AND IMAGEVIEWS
        namaDetailTextView.setText(nama);
        nikDetailTextView.setText(nik);
        dateDetailTextView.setText("DATE: "+getDateToday());
        Picasso.with(this)
                .load(imageURL)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(fotoKtpImageView);

    }

}
