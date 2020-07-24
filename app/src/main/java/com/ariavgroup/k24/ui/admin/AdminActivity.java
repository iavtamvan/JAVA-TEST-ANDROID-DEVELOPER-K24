package com.ariavgroup.k24.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ariavgroup.k24.R;
import com.ariavgroup.k24.adapter.MemberAdapter;
import com.ariavgroup.k24.model.AkunModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.ariavgroup.k24.util.MyApp.db;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView rv;
    private MemberAdapter memberAdapter;
    private List<AkunModel> akunModels;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
        getSupportActionBar().setTitle("Data Member");
        akunModels = new ArrayList<>();
        getDataMembers();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TambahMemberActivity.class));
            }
        });

    }

    private void getDataMembers() {
        akunModels = db.akunDao().findByRule("member");
        memberAdapter = new MemberAdapter(getApplicationContext(), akunModels);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(memberAdapter);
        memberAdapter.notifyDataSetChanged();

        //just checking data from db
        for (int i = 0; i < akunModels.size(); i++) {
            Log.e("Aplikasi", akunModels.get(i).getNama() + i);
            Log.e("Aplikasi", akunModels.get(i).getPassword() + i);
            Log.e("Aplikasi", akunModels.get(i).getUsername() + i);
            Log.e("Aplikasi", akunModels.get(i).getAlamat() + i);
        }
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);
    }
}