package com.example.mewwallet.models;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.example.mewwallet.utilities.Crypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;

public class Block {

    private int index;
    private long timestamp;
    private String hash;
    private String previousHash;
    private List<Transaction> transactions;
    private int nonce;

    public Block(int index, long timestamp, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        nonce = 0;
        hash = Block.calculateHash(this);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String toString() {
        return index + timestamp + previousHash + transactions.toString() + nonce;
    }

    public static String calculateHash(Block block) {
        String calculatedhash = Crypt.sha256(block.toString());

        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        nonce = 0;

//        while (!getHash().substring(0,  difficulty).equals(Utils.zeros(difficulty))) {
//            nonce++;
//            hash = Block.calculateHash(this);
//        }
    }

    public boolean hasValidTransactions() {
        for (Transaction transaction: this.transactions) {
            if (!transaction.isValid()) {
                return false;
            }
        }

        return true;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}
