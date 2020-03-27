package com.bstu.fit.yarmolik.cinema;

public class CinemaModel {
    String cardName;
    int imageResourceId;
    public CinemaModel(int imageResourceId, String cardName){
        this.cardName=cardName;
        this.imageResourceId=imageResourceId;
    }
    public void setImageResourceId(int imageResourceId){
        this.imageResourceId=imageResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }
}
