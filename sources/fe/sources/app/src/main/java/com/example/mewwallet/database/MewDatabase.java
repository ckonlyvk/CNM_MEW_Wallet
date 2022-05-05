package com.example.mewwallet.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.mewwallet.dao.WalletDao;
import com.example.mewwallet.models.Wallet;

@Database(entities = {Wallet.class}, version=1, exportSchema = false)
public abstract class MewDatabase extends RoomDatabase {
    private static MewDatabase mewDatabase;

    public static synchronized MewDatabase getTodoDatabase(Context context) {
        if(mewDatabase == null) {
            mewDatabase = Room.databaseBuilder(
                    context,
                    MewDatabase.class,
                    "mew_database")
                    .build();
        }
        return mewDatabase;
    }

    public abstract WalletDao walletDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}