package com.ariavgroup.k24.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AkunModel {

    @PrimaryKey(autoGenerate = true)
    int kode_member;
    @ColumnInfo(name = "nama")
    String nama;
    @ColumnInfo(name = "tgl_lahir")
    String tgl_lahir;
    @ColumnInfo(name = "alamat")
    String alamat;
    @ColumnInfo(name = "jenis_kelamin")
    String jenis_kelamin;
    @ColumnInfo(name = "username")
    String username;
    @ColumnInfo(name = "password")
    String password;
    @ColumnInfo(name = "rule")
    String rule;

    public int getKode_member() {
        return kode_member;
    }

    public void setKode_member(int kode_member) {
        this.kode_member = kode_member;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
