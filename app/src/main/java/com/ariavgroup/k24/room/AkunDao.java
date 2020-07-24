package com.ariavgroup.k24.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ariavgroup.k24.model.AkunModel;

import java.util.List;

@Dao
public interface AkunDao {
    @Query("SELECT * FROM akunmodel")
    List<AkunModel> getMembers();

    @Query("SELECT * FROM akunmodel WHERE username LIKE :username")
    AkunModel findByUsername(String username);

    @Query("SELECT * FROM akunmodel WHERE rule LIKE :rule")
    List<AkunModel> findByRule(String rule);

//    @Query("DELETE FROM akunmodel WHERE token =:token ")
//    void hapus(String token);

    @Query("UPDATE akunmodel SET password =:pass WHERE username=:username")
    void updatePass(String pass, String username);


    // TODO tambahan saja bawah ini
    @Insert
    void insertAll(AkunModel akunModel);



}
