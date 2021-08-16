package com.ardi.apppenyimpananktp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//Inisiasi tombol menu halaman depan
// bukaDataKtp : tombol untuk ke menu Daftar KTP
// tambahData : tombol untuk ke menu Tambah Data

    private Button bukaDataKtp,tambahData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        bukaDataKtp = findViewById ( R.id.bukaDataKtp );
        tambahData = findViewById ( R.id.tambahData );

        bukaDataKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ItemsActivity.class);
                startActivity(i);
            }
        });
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(i);
            }
        });

    }
}
