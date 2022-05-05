package com.example.mewwallet.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mewwallet.databinding.BottomsheetCreatePinCodeBinding;
import com.example.mewwallet.databinding.BottomsheetCreateWalletOverviewBinding;
import com.example.mewwallet.models.Wallet;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreatePinCodeBottomSheet extends BottomSheetDialogFragment {
    private static final String TAG = CreatePinCodeBottomSheet.class.toString();

    private BottomsheetCreatePinCodeBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = BottomsheetCreatePinCodeBinding.inflate(
                LayoutInflater.from(getActivity()),
                null, false);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(binding.getRoot());

        setListeners(bottomSheetDialog);

        return bottomSheetDialog;
    }

    private void setListeners(BottomSheetDialog bottomSheetDialog) {
        binding.imageBack.setOnClickListener(view -> bottomSheetDialog.dismiss());
        binding.edittextInputPasscode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 6) {
                    navigateToConfirmCreateWalletBottomsheet(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void navigateToConfirmCreateWalletBottomsheet(String passcode) {
        Wallet wallet = new Wallet();
        wallet.setPasscode(passcode);

        FragmentManager fm = getChildFragmentManager();
        ConfirmCreateWalletBottomSheet bottomSheet = ConfirmCreateWalletBottomSheet.newInstance(wallet);
        bottomSheet.show(fm, TAG);
    }
}