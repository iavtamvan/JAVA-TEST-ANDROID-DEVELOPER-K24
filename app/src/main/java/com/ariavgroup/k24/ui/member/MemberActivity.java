package com.ariavgroup.k24.ui.member;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ariavgroup.k24.R;
import com.ariavgroup.k24.model.AkunModel;

import java.util.ArrayList;
import java.util.List;

import static com.ariavgroup.k24.util.MyApp.db;

public class MemberActivity extends AppCompatActivity {
    private String username;

    private AkunModel akunModels;
    private TextView tvIdMembers;
    private TextView tvNamaLengkap;
    private TextView tvTglLahir;
    private TextView tvAlamat;
    private TextView tvJenisKelamin;
    private TextView tvUsername;
    private CardView cvKlikEditPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        initView();
        username = getIntent().getStringExtra("username");
        akunModels = db.akunDao().findByUsername(username);
        tvIdMembers.setText("K24-N." + akunModels.getKode_member() + "-20");
        tvNamaLengkap.setText(akunModels.getNama());
        tvTglLahir.setText(akunModels.getTgl_lahir());
        tvAlamat.setText(akunModels.getAlamat());
        tvJenisKelamin.setText(akunModels.getJenis_kelamin());
        tvUsername.setText(akunModels.getUsername());

        cvKlikEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordMemberActivity.class);
                intent.putExtra("user", akunModels.getUsername());
                intent.putExtra("pass", akunModels.getPassword());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tvIdMembers = findViewById(R.id.tv_id_members);
        tvNamaLengkap = findViewById(R.id.tv_nama_lengkap);
        tvTglLahir = findViewById(R.id.tv_tgl_lahir);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvJenisKelamin = findViewById(R.id.tv_jenis_kelamin);
        tvUsername = findViewById(R.id.tv_username);
        cvKlikEditPassword = findViewById(R.id.cv_klik_edit_password);
    }
}