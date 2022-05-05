package com.example.mewwallet.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.mewwallet.R;
import com.example.mewwallet.databinding.ActivityMainBinding;
import com.example.mewwallet.fragments.CreateWalletOverviewBottomSheet;
import com.example.mewwallet.fragments.WalletsFragment;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.toString();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_wallets);
        if (fragment == null) {
            fragment = new WalletsFragment();
            fm.beginTransaction()
                    .replace(R.id.fragment_wallets, fragment)
                    .commit();
        }
    }

//    public void setListeners() {
//        binding.wa.setOnClickListener(view -> showCreateWalletBottomSheet());
//    }
//
//    private void showCreateWalletBottomSheet() {
//        FragmentManager fm = getSupportFragmentManager();
//        CreateWalletOverviewBottomSheet bottomSheet = new CreateWalletOverviewBottomSheet();
//        bottomSheet.show(fm, TAG);
//    }
//
//    int count = getNumberOfSongsAvailable();
//    Resources res = getResources();
//    String songsFound = res.getQuantityString(R.plurals.numberOfSongsAvailable, count, count);
}