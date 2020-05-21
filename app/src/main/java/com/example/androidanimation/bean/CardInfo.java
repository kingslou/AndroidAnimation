package com.example.androidanimation.bean;

import java.util.Objects;

/**
 * @author 86153
 */
public class CardInfo {

    private int cardId;
    private String cardName;
    private String cardNameBackGround;
    private boolean isBg;
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

    public void setBg(boolean bg) {
        isBg = bg;
    }

    public boolean isBg() {
        return isBg;
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

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardInfo cardInfo = (CardInfo) o;
        return cardId == cardInfo.cardId &&
                Objects.equals(cardName, cardInfo.cardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, cardName);
    }
}
