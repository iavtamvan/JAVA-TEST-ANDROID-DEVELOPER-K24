package com.ariavgroup.k24.ui.member;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ariavgroup.k24.R;
import com.ariavgroup.k24.ui.LoginActivity;

import static com.ariavgroup.k24.util.MyApp.db;

public class ChangePasswordMemberActivity extends AppCompatActivity {

    private EditText edtPasswordBefore;
    private EditText edtPasswordAfter;
    private Button btnEditPassword;

    private String passBefore;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_member);
        getSupportActionBar().setTitle("Ganti password");
        initView();

        username = getIntent().getStringExtra("user");
        passBefore = getIntent().getStringExtra("pass");

        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPasswordBefore.getText().toString().trim().isEmpty() || edtPasswordAfter.getText().toString().trim().isEmpty()){
                    Toast.makeText(ChangePasswordMemberActivity.this, "Lengkapi password anda", Toast.LENGTH_SHORT).show();
                } else if (!edtPasswordBefore.getText().toString().trim().equals(passBefore)){
                    Toast.makeText(ChangePasswordMemberActivity.this, "Password lama tidak benar", Toast.LENGTH_SHORT).show();
                } else {
                    db.akunDao().updatePass(edtPasswordAfter.getText().toString().trim(), username);
                    Toast.makeText(ChangePasswordMemberActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    finishAffinity();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
    }

    private void initView() {
        edtPasswordBefore = findViewById(R.id.edt_password_before);
        edtPasswordAfter = findViewById(R.id.edt_password_after);
        btnEditPassword = findViewById(R.id.btn_edit_password);
    }
}