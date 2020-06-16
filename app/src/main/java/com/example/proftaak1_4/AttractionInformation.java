package com.example.proftaak1_4;


import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class AttractionInformation implements Serializable {


    private String title;
    private String typeOfAttraction;
    private boolean unlocked;
    private int drawable;

    public int getDrawable() {
        return drawable;
    }

    public AttractionInformation(String title, String typeOfAttraction, int drawable) {
        this.title = title;
        this.typeOfAttraction = typeOfAttraction;
        this.unlocked = false;
        this.drawable = drawable;
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
            case "end":
                text = "De boze tovenaar Schmargamel was ooit eens een goede tovenaar, tot dat hij zijn plezier verloor en wenste dat niemand meer plezier mocht hebben. Daarom heeft de boze tovenaar het pretpark beinvloed, maar dankzij jou is Schargamel tegengehouden en verdreven van het park.";
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
