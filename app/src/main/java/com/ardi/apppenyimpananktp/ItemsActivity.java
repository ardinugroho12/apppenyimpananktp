package com.ardi.apppenyimpananktp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener, SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private FirebaseRecyclerOptions<Ktp> options;
    private RecyclerAdapter adapter;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Ktp> mKtps;


    private void openDetailActivity(String[] data){
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("NAMA_KEY",data[0]);
        intent.putExtra("NIK_KEY",data[1]);
        intent.putExtra("IMAGE_KEY",data[2]);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_items );


        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        mKtps = new ArrayList<>();
        mAdapter = new RecyclerAdapter (ItemsActivity.this, mKtps);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ItemsActivity.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("KTP");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mKtps.clear();

                for (DataSnapshot ktpSnapshot : dataSnapshot.getChildren()) {
                    Ktp upload = ktpSnapshot.getValue(Ktp.class);
                    upload.setKey(ktpSnapshot.getKey());
                    mKtps.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ItemsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onItemClick(int position) {
        Ktp clickedKtp=mKtps.get(position);
        String[] ktpData={clickedKtp.getnama(),clickedKtp.getnik(),clickedKtp.getImageUrl()};
        openDetailActivity(ktpData);
    }

    @Override
    public void onShowItemClick(int position) {
        Ktp clickedKtp=mKtps.get(position);
        String[] ktpData={clickedKtp.getnama(),clickedKtp.getnik(),clickedKtp.getImageUrl()};
        openDetailActivity(ktpData);
    }


    @Override
    public void onDeleteItemClick(int position) {
        Ktp selectedItem = mKtps.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void> () {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ItemsActivity.this, "Data Dihapus", Toast.LENGTH_SHORT).show();
            }
        });

    }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu options from the res/menu/menu_editor.xml file.
            // This adds menu items to the app bar.
            getMenuInflater().inflate(R.menu.searchmenu, menu);
            MenuItem searchItem = menu.findItem(R.id.cariData);
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(this);
            searchView.setIconified(true);
            searchView.setQueryHint("Cari...");
            return true;
        }
        @Override
        public boolean onQueryTextSubmit(String query) {

            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            Query fireBaseSearchQuery = mDatabaseRef.orderByChild("nama").startAt(query).endAt(query + "\uf8ff");

            fireBaseSearchQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mKtps.clear();
                    if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                        for (DataSnapshot ktpSnapshot : dataSnapshot.getChildren()) {
                            Ktp upload = ktpSnapshot.getValue(Ktp.class);
                            upload.setKey(ktpSnapshot.getKey());
                            mKtps.add(upload);
                        }
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ItemsActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ItemsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            return false;
        }


}
