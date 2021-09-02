package com.devnweyi.carrental.viewmodel;

public class StartViewModel {

    EventListener eventListener;

    public StartViewModel(EventListener eventListener){
        this.eventListener=eventListener;
    }

    public void onSignUpClicked(){
        if(eventListener!=null)eventListener.onSignUpClicked();
    }

    public void onSignInClicked(){
        if(eventListener!=null)eventListener.onSignInClicked();
    }

    public interface EventListener{
        void onSignUpClicked();
        void onSignInClicked();
    }
}
