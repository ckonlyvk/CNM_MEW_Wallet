package com.example.mewwallet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.mewwallet.adapters.WalletsAdapter;
import com.example.mewwallet.databinding.FragmentWalletsBinding;
import com.example.mewwallet.models.Wallet;
import com.example.mewwallet.viewmodels.WalletsViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WalletsFragment extends Fragment {
    private static final String TAG = WalletsFragment.class.toString();
    private FragmentWalletsBinding binding;
    private WalletsViewModel walletsViewModel;

    private WalletsAdapter adapter;
    private List<Wallet> wallets;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWalletsBinding.inflate(inflater, container, false);

        init();

        setListeners();

        return binding.getRoot();
    }

    private void init() {
        walletsViewModel = new ViewModelProvider(this).get(WalletsViewModel.class);
        wallets = new ArrayList<>();
        adapter = new WalletsAdapter(wallets);
        binding.recyclerviewWallets.setAdapter(adapter);
        loadWallets();
    }

    private void loadWallets() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(walletsViewModel.loadWallets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallets -> {
                    if(wallets.size()>0) {
                        this.wallets = wallets;
                        binding.recyclerviewWallets.setVisibility(View.VISIBLE);
                        adapter.setWallets(wallets, getActivity());
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        binding.recyclerviewWallets.setVisibility(View.GONE);
                    }
                    compositeDisposable.dispose();
                }));
    }

    private void setListeners() {
        binding.bannerCreateWallet.setOnClickListener(view -> showCreateWalletBottomSheet());
    }

    private void showCreateWalletBottomSheet() {
        FragmentManager fm = getParentFragmentManager();
        CreateWalletOverviewBottomSheet bottomSheet = new CreateWalletOverviewBottomSheet();
        bottomSheet.show(fm, TAG);
    }
}
