package com.ariavgroup.k24.ui.admin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ariavgroup.k24.R;
import com.ariavgroup.k24.model.AkunModel;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.OkHttpClient;

import static com.ariavgroup.k24.util.MyApp.db;

public class TambahMemberActivity extends AppCompatActivity {

    private String jenisKelamin[] = {"-- PILIH --", "Laki - Laki", "Perempuan"};
    private String jenisKelaminSave;

    private EditText edtNamaLengkap;
    private EditText edtTglLahir;
    private EditText edtAlamat;
    private EditText edtUsename;
    private EditText edtPassword;
    private TextView tvTglLahir;
    private LinearLayout divTglLahir;
    private Button btnKirim;
    private MaterialSpinner spnJenisKelamin;

    private AkunModel akunModel;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_member);
        getSupportActionBar().setTitle("Tambah Data Member");
        initView();
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        divTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        spnJenisKelamin.setItems(jenisKelamin);
        spnJenisKelamin.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                jenisKelaminSave = item;
                Snackbar.make(view, "Memilih " + jenisKelaminSave, Snackbar.LENGTH_LONG).show();
            }
        });

        akunModel = new AkunModel();
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                akunModel.setNama(edtNamaLengkap.getText().toString().trim());
                akunModel.setTgl_lahir(tvTglLahir.getText().toString().trim());
                akunModel.setAlamat(edtAlamat.getText().toString().trim());
                akunModel.setJenis_kelamin(jenisKelaminSave);
                akunModel.setUsername(edtUsename.getText().toString().trim());
                akunModel.setPassword(edtPassword.getText().toString().trim());
                akunModel.setRule("member");
                db.akunDao().insertAll(akunModel);
                Toast.makeText(TambahMemberActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                finishAffinity();
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            }
        });
    }

    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvTglLahir.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void initView() {
        edtNamaLengkap = findViewById(R.id.edt_nama_lengkap);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtUsename = findViewById(R.id.edt_usename);
        edtPassword = findViewById(R.id.edt_password);
        btnKirim = findViewById(R.id.btn_kirim);
        divTglLahir = findViewById(R.id.div_tgl_lahir);
        tvTglLahir = findViewById(R.id.tv_tgl_lahir);
        spnJenisKelamin = findViewById(R.id.spn_jenis_kelamin);
    }
}