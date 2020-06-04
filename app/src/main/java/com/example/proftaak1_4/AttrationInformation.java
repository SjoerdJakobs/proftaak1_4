package com.example.proftaak1_4;


import java.io.Serializable;

public class AttrationInformation implements Serializable {


    private String title;
    private String typeOfAttraction;
    private boolean unlocked;

    public AttrationInformation(String title, String typeOfAttraction) {
        this.title = title;
        this.typeOfAttraction = typeOfAttraction;
        this.unlocked = false;
    }


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

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}
