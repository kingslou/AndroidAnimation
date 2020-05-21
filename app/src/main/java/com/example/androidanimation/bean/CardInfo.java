package com.example.androidanimation.bean;

/**
 * @author 86153
 */
public class CardInfo {

    private String cardName;
    private String cardNameBackGround;
    private boolean isFront;
    private boolean needCover;
    //正确标记
    private String answerSign;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNameBackGround() {
        return cardNameBackGround;
    }

    public void setCardNameBackGround(String cardNameBackGround) {
        this.cardNameBackGround = cardNameBackGround;
    }

    public void setFront(boolean front) {
        isFront = front;
    }

    public boolean isFront() {
        return isFront;
    }

    public void setNeedCover(boolean needCover) {
        this.needCover = needCover;
    }

    public boolean isNeedCover() {
        return needCover;
    }

    public void setAnswerSign(String answerSign) {
        this.answerSign = answerSign;
    }

    public String getAnswerSign() {
        return answerSign;
    }
}
