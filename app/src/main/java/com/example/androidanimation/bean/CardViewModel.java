package com.example.androidanimation.bean;

import android.view.View;

public class CardViewModel {

    private CardInfo cardInfo;
    private View view;
    private int cardPosition;

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setCardPosition(int cardPosition) {
        this.cardPosition = cardPosition;
    }

    public int getCardPosition() {
        return cardPosition;
    }
}
