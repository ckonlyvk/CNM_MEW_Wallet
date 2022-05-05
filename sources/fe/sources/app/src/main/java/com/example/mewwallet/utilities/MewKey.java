package com.example.mewwallet.utilities;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MewKey {
    public static KeyPair generateKeyPair() {
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        kpg.initialize(1024);
        return kpg.generateKeyPair();
    }

    public static String encodedKey(Key key) {
//        return Base64.getEncoder().encodeToString(key.getEncoded());
        return MewKey.bytesToHex(key.getEncoded());
    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static byte[] hexToBytes(String hex) {
        int it = Integer.parseInt(hex, 16);
        BigInteger bigInt = BigInteger.valueOf(it);
        byte[] bytearray = (bigInt.toByteArray());

        return bytearray;
    }

    public static PrivateKey decodedPrivateKey(String encodedPrivateKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedPrivateKey);
        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(decodedKey);
        try {
            PrivateKey pri = kf.generatePrivate(ks);

            return pri;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public static PublicKey decodedPublicKey(String encodedPublicKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedPublicKey);
        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        X509EncodedKeySpec ks = new X509EncodedKeySpec(decodedKey);
        try {
            PublicKey pub = kf.generatePublic(ks);

            return pub;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
