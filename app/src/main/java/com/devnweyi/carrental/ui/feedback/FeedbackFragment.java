package com.devnweyi.carrental.ui.feedback;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devnweyi.carrental.R;

public class FeedbackFragment extends Fragment {

    private FeedbackViewModel mViewModel;
    static final String TAG="FeedbackFragment";

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.i(TAG,"on create view");

        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedbackViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Log.i(TAG,"on create");
    }

    @Override
    public void onViewCreated(View view,Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);

        Log.i(TAG,"on view created");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);

        Log.i(TAG,"on view state restored");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG,"on start");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG,"on resume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG,"on pause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG,"on stop");
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG,"on save instance state");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.i(TAG,"on destroy view");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"on destroy");
    }

}