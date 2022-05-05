package com.example.mewwallet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mewwallet.models.Wallet;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface WalletDao {
    @Query("SELECT * FROM wallet")
    Flowable<List<Wallet>> getWallets();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addWallet(Wallet wallet);

}