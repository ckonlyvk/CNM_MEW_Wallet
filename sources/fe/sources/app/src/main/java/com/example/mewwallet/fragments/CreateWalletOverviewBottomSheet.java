package com.example.mewwallet.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mewwallet.databinding.BottomsheetCreateWalletOverviewBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateWalletOverviewBottomSheet extends BottomSheetDialogFragment {
    private static final String TAG = CreateWalletOverviewBottomSheet.class.toString();

    private BottomsheetCreateWalletOverviewBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = BottomsheetCreateWalletOverviewBinding.inflate(
                LayoutInflater.from(getActivity()),
                null, false);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(binding.getRoot());

        setListeners(bottomSheetDialog);

        return bottomSheetDialog;
    }

    private void setListeners(BottomSheetDialog bottomSheetDialog) {
        binding.imageCloseBottomSheet.setOnClickListener(view -> bottomSheetDialog.dismiss());
        binding.buttonCreateWallet.setOnClickListener(view -> navigateToPinCodeBottomsheet());
    }

    private void navigateToPinCodeBottomsheet() {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        CreatePinCodeBottomSheet bottomSheet = new CreatePinCodeBottomSheet();
        bottomSheet.show(fm, TAG);
    }
}
