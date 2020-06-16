package com.example.proftaak1_4.ReadWriteData;

import com.example.proftaak1_4.AttractionInformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SessionData implements Serializable
{
    private boolean hasFirstPart;
    private boolean hasSecondPart;
    private boolean hasCompleted;

    private AttractionInformation secretEnd;

    private ArrayList<AttractionInformation> allAttractions;
    private boolean firstStart;

    private HashMap<String, String> topicMsg;


    SessionData()
    {
        this.hasFirstPart = false;
        this.hasSecondPart = false;
        this.hasCompleted = false;

        this.allAttractions = new ArrayList<>();
        this.firstStart = true;
        this.topicMsg = new HashMap<>();
        this.secretEnd = null;
    }

    public boolean isHasFirstPart() {
        return hasFirstPart;
    }

    public void setHasFirstPart(boolean hasFirstPart) {
        this.hasFirstPart = hasFirstPart;
    }

    public boolean isHasSecondPart() {
        return hasSecondPart;
    }

    public void setHasSecondPart(boolean hasSecondPart) {
        this.hasSecondPart = hasSecondPart;
    }

    public ArrayList<AttractionInformation> getAllAttractions() {
        return allAttractions;
    }

    public void setAllAttractions(ArrayList<AttractionInformation> allAttractions) {
        this.allAttractions = allAttractions;
    }

    public boolean isFirstStart() {
        return firstStart;
    }

    public void setFirstStart(boolean firstStart) {
        this.firstStart = firstStart;
    }

    public HashMap<String, String> getTopicMsg() {
        return topicMsg;
    }

    public void setTopicMsg(HashMap<String, String> topicMsg) {
        this.topicMsg = topicMsg;
    }


    public boolean isHasCompleted() {
        return hasCompleted;
    }

    public void setHasCompleted(boolean hasCompleted) {
        this.hasCompleted = hasCompleted;
    }

    public AttractionInformation getSecretEnd() {
        return secretEnd;
    }

    public void setSecretEnd(AttractionInformation secretEnd) {
        this.secretEnd = secretEnd;
    }
}
