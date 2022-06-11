package com.devnweyi.carrental.ui.booking;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.databinding.FragmentBookingBinding;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.model.BookingModel;

import java.util.ArrayList;
import java.util.List;

public class BookingFragment extends Fragment implements BookingDetailViewModel.BookingDetailListener {

    private FragmentBookingBinding binding;
    SharedPreferences sharedpreferences;
    private BookingModel bookingModel=new BookingModel();
    private List<BookingModel> lstBooking=new ArrayList<>();
    private BookingFragment context=this;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBookingBinding.inflate(inflater, container, false);
        sharedpreferences= getContext().getSharedPreferences(SystemSetting.MyPREFERENCES, Context.MODE_PRIVATE);
        int userId=sharedpreferences.getInt(SystemSetting.UserID,0);
        getBookingDetail(userId);

        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getBookingDetail(int userId) {
        (Api.getClient().getBooking(userId)).enqueue(new Callback<List<BookingModel>>() {
            @Override
            public void onResponse(Call<List<BookingModel>> call, Response<List<BookingModel>> response) {
                lstBooking = response.body();
                if (lstBooking != null && lstBooking.size() != 0) bookingModel = lstBooking.get(0);
                binding.setViewModel(new BookingDetailViewModel(getContext(), bookingModel, context));
            }

            @Override
            public void onFailure(Call<List<BookingModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.e("error", t.toString());
            }
        });
    }

    @Override
    public void onCancelBookingSuccess(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancelBookingFail(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }
}