package com.devnweyi.carrental.general;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.databinding.DialogSortBinding;
import com.devnweyi.carrental.ui.searchcar.SearchCarFragment;
import com.devnweyi.carrental.ui.searchcar.SearchCarViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

public class SortDialog extends DialogFragment implements SearchCarViewModel.SortDialogListener {

    DialogSortBinding binding;
    private static SortDialog dialog;
    private static Context context;
    AlertDialog showDialog;

    public static SortDialog newInstance(String myInfo, Context context1) {
        dialog = new SortDialog();
        context = context1;
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        SearchCarFragment searchCarFragment=new SearchCarFragment();
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_sort, null,false);
        binding.setViewModel(new SearchCarViewModel(searchCarFragment.getContext(),this,new SearchCarFragment()));

        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setView(binding.getRoot());

        dialog.setCancelable(true);
        showDialog=dialog.create();

        return showDialog;
    }

    @Override
    public void onDialogCloseClicked() {
        showDialog.dismiss();
    }
}
