package com.example.mewwallet.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mewwallet.R;
import com.example.mewwallet.databinding.ItemContainerWalletBinding;
import com.example.mewwallet.models.Wallet;
import com.example.mewwallet.utilities.Constants;
import com.example.mewwallet.utilities.MewKey;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

public class WalletsAdapter extends RecyclerView.Adapter<WalletsAdapter.WalletViewHolder> {
    private List<Wallet> wallets;
    private Context context;

    public void setWallets(List<Wallet> wallets, Context context) {
        this.wallets = wallets;
        this.context = context;
    }

    public WalletsAdapter(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemContainerWalletBinding binding = ItemContainerWalletBinding.inflate(
                inflater, parent, false);

        return new WalletViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, int position) {
        holder.bindData(wallets.get(position));
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    class WalletViewHolder extends RecyclerView.ViewHolder {
        private ItemContainerWalletBinding binding;

        public WalletViewHolder(@NonNull ItemContainerWalletBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            setListeners();
        }

        public void bindData(Wallet wallet) {
            Resources res = context.getResources();
            binding.textAddress.setText(String.format(context.getString(R.string.hex),wallet.getPublicKey()));

            double coin = wallet.getAmount();
            String numCoin = res.getQuantityString(R.plurals.number_coin, (int) coin, Double.toString(coin));
            binding.textCoin.setText(numCoin);

            binding.textRatio.setText(String.format(context.getString(R.string.ratio), 1, Double.toString(Constants.ratioCoinToUSD)));
        }

        private void setListeners() {
            binding.imageCopy.setOnClickListener(view -> {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", binding.textAddress.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copied Text", Toast.LENGTH_SHORT).show();
                binding.imageCopy.setColorFilter(ContextCompat.getColor(context, R.color.secondary_icon));
            });
        }

    }
}
