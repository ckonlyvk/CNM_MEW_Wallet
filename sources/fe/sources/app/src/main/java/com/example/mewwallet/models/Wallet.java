package com.example.mewwallet.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "wallet")
public class Wallet implements Serializable {
    @PrimaryKey
    @NonNull
    @SerializedName("publicKey")
    String publicKey;

    @NonNull
    @SerializedName("privateKey")
    String privateKey;

    @NonNull
    @SerializedName("passcode")
    String passcode;


    @NonNull
    @SerializedName("amount")
    double amount;

    public Wallet() {
        this.amount = 10.0;
    }

    public Wallet(@NonNull String publicKey, @NonNull String privateKey, @NonNull String passcode, double amount) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.passcode = passcode;
        this.amount = amount;
    }

    @NonNull
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(@NonNull String publicKey) {
        this.publicKey = publicKey;
    }

    @NonNull
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(@NonNull String privateKey) {
        this.privateKey = privateKey;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @NonNull
    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(@NonNull String passcode) {
        this.passcode = passcode;
    }
}
