package com.example.mewwallet.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.quickaccesswallet.WalletServiceEvent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.mewwallet.activites.MainActivity;
import com.example.mewwallet.databinding.BottomsheetConfirmCreateWalletBinding;
import com.example.mewwallet.databinding.BottomsheetCreatePinCodeBinding;
import com.example.mewwallet.models.Wallet;
import com.example.mewwallet.utilities.MewKey;
import com.example.mewwallet.viewmodels.WalletsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ConfirmCreateWalletBottomSheet  extends BottomSheetDialogFragment {
    private static final String TAG = CreatePinCodeBottomSheet.class.toString();
    public static String WALLET_ARGS = "WALLET_ARGS";
    private BottomsheetConfirmCreateWalletBinding binding;

    private WalletsViewModel walletsViewModel;

    private Wallet wallet;

    public static ConfirmCreateWalletBottomSheet newInstance(Wallet wallet) {
        ConfirmCreateWalletBottomSheet bottomSheet = new ConfirmCreateWalletBottomSheet();
        Bundle bundle = new Bundle();
        bundle.putSerializable(WALLET_ARGS, wallet);
        bottomSheet.setArguments(bundle);
        return bottomSheet;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            wallet = (Wallet) bundle.getSerializable(WALLET_ARGS);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = BottomsheetConfirmCreateWalletBinding.inflate(
                LayoutInflater.from(getActivity()),
                null, false);

        walletsViewModel = new ViewModelProvider(this).get(WalletsViewModel.class);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(binding.getRoot());

        setListeners(bottomSheetDialog);

        return bottomSheetDialog;
    }

    private void setListeners(BottomSheetDialog bottomSheetDialog) {
        binding.buttonCreateWallet.setOnClickListener(view -> {
            generateAddress();
            addWallet(wallet);
        });
    }

    private void generateAddress() {
        //Genera
        KeyPair keyPair = MewKey.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();

        String encodedPublicKey = MewKey.encodedKey(publicKey);
        String encodedPrivateKey = MewKey.encodedKey(privateKey);

        System.out.println(encodedPublicKey);
        System.out.println(encodedPrivateKey);

        wallet.setPublicKey(encodedPublicKey);
        wallet.setPrivateKey(encodedPrivateKey);

//        PublicKey pub = MewKey.decodedPublicKey(encodedPublicKey);
//        PrivateKey pri = MewKey.decodedPrivateKey(encodedPrivateKey);
//        System.out.println(pub);
//        System.out.println(pri);


    }

    private void addWallet(Wallet wallet) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(walletsViewModel.addWallet(wallet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    compositeDisposable.dispose();
                }));
    }
}
