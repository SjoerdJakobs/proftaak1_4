package com.example.proftaak1_4;

import android.media.Image;

import java.io.Serializable;

public class AttrationInformation implements Serializable {

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
            case "cobra":
                text = "prepare to be astonished! here is the cobra ";
                break;

            case "fabelwoud":
                text = "Here is the fabel woud ladies and gentlemen";
                break;
        }

        return text;
    }

    public String getCaption(){
        return title;
    }

    public String getTypeOfAttraction() {
        return typeOfAttraction;
    }
}
