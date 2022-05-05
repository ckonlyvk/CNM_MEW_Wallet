package com.example.mewwallet.models;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.example.mewwallet.utilities.Crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Transaction {

    private String senderAddress;
    private String receiverAddress;
    private double amount;
    private String signature;

    public Transaction(String senderAddress, String receiverAddress, double amount) {
        this.senderAddress = senderAddress;
        this.receiverAddress = receiverAddress;
        this.amount = amount;
    }

    public String calculateHash() {
        return Crypt.sha256(this.senderAddress + this.receiverAddress + this.amount);
    }

//    public void signTransaction(signingKey) {
//        if(signingKey.getPublic("hex") !== this.senderAddress) {
//            throw new Error("You cannot sign transaction for other wallet");
//        }
//        const hashTransaction = this.calculateHash();
//        const sign = signingKey.sign(hashTransaction, "base64");
//        this.signature = sign.toDER("hex");
//    }

    public boolean isValid() {
        if(this.senderAddress == null) return true;
        if(this.signature == null || this.signature.isEmpty()) {
            throw new Error("No signature in this transaction");
        }

        return true;
//        String publicKey = ec.keyFromPublic(this.senderAddress, "hex");
//        return publicKey.verify(this.calculateHash(), this.signature);
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
