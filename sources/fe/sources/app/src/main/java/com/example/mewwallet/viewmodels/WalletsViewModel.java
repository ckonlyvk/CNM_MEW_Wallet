package com.example.mewwallet.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mewwallet.database.MewDatabase;
import com.example.mewwallet.models.Wallet;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WalletsViewModel extends AndroidViewModel {
    private MewDatabase mewDatabase;

    public WalletsViewModel(@NonNull Application application) {
        super(application);
        mewDatabase = MewDatabase.getTodoDatabase(application);
    }

    public Flowable<List<Wallet>> loadWallets() {
        return mewDatabase.walletDao().getWallets();
    }

    public Completable addWallet(Wallet wallet) {
        return mewDatabase.walletDao().addWallet(wallet);
    }
}
