package com.ariavgroup.k24.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ariavgroup.k24.R;
import com.ariavgroup.k24.model.AkunModel;
import com.ariavgroup.k24.ui.admin.AdminActivity;
import com.ariavgroup.k24.ui.member.MemberActivity;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

import static com.ariavgroup.k24.util.MyApp.db;

public class LoginActivity extends AppCompatActivity {

    private TextView tvVersion;
    private EditText edtUsername;
    private EditText edtPassword;

    private String username;
    private String rule;

    private Button btnLogin;

    private AkunModel akunModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getSupportActionBar().hide();
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        akunModel = new AkunModel();
        akunModel.setNama("admin-k24");
        akunModel.setTgl_lahir("30/07/2020");
        akunModel.setAlamat("Yogyakarta");
        akunModel.setJenis_kelamin("l");
        akunModel.setUsername("admin");
        akunModel.setPassword("123");
        akunModel.setRule("admin");
        db.akunDao().insertAll(akunModel);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                akunModel = db.akunDao().findByUsername(edtUsername.getText().toString().trim());
                Log.d("findUser: ", "findUser: " + akunModel.getAlamat());
                username = akunModel.getUsername();
                rule = akunModel.getRule();
                if (edtUsername.getText().toString().trim().isEmpty() || edtPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Masukan akun anda dengan baik", Toast.LENGTH_SHORT).show();
                } else if (!edtUsername.getText().toString().trim().equals(username) || !edtPassword.getText().toString().trim().equals(akunModel.getPassword())){
                    Toast.makeText(LoginActivity.this, "Username/password salah", Toast.LENGTH_SHORT).show();
                } else {
                    if (rule.equalsIgnoreCase("admin")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    } else if (rule.equalsIgnoreCase("member")){
                        Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        Toast.makeText(LoginActivity.this, "Tidak ada akun", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

    private void initView() {
        tvVersion = findViewById(R.id.tv_version);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }
}