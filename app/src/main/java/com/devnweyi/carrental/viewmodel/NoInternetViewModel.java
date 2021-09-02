package com.devnweyi.carrental.viewmodel;

public class NoInternetViewModel {

    EventListener eventListener;

    public NoInternetViewModel(EventListener eventListener){
        this.eventListener=eventListener;
    }

    public void onCloseClicked(){
        if(eventListener!=null)eventListener.onCloseClicked();
    }

    public void onRetryClicked(){
        if(eventListener!=null)eventListener.onRetryClicked();
    }

    public interface EventListener{
        void onCloseClicked();
        void onRetryClicked();
    }
}
