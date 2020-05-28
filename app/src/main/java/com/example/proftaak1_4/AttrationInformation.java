package com.example.proftaak1_4;

import android.media.Image;

public class AttrationInformation {

    private int imageID;
    private String title;
    private String typeOfAttraction;

//hiet alleen nog even de foto meegeven, int imageID,
    public AttrationInformation(String title, String typeOfAttraction) {
//        this.imageID = imageID;
        this.title = title;
        this.typeOfAttraction = typeOfAttraction;
    }

//    public int getImageID() {
//        return imageID;
//    }

    public String getTitle() {
        return title;
    }

    public String getText() {

        String text = "";

        switch (typeOfAttraction){
            case "Cobra":
                text = "";
                break;

            case "FabelWoud":
                text = "";
                break;
        }

        return text;
    }
}
