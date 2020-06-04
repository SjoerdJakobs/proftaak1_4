package com.example.proftaak1_4.ReadWriteData;

import com.example.proftaak1_4.AttractionInfoActivity;
import com.example.proftaak1_4.AttrationInformation;

import java.io.Serializable;
import java.util.ArrayList;

public class SessionData implements Serializable
{
    private String userName;

    private String code1;
    private String code2;
    private String code3;
    private String code4;

    private boolean hasFirstPart;
    private boolean hasSecondPart;
    private boolean hasThirdPart;
    private boolean hasFourthPart;

    private ArrayList<AttrationInformation> allAttractions;
    private boolean firstStart;


    SessionData()
    {
        this.userName = "noname";
        this.code1 = "nocode1";
        this.code2 = "nocode2";
        this.code3 = "nocode3";
        this.code4 = "nocode4";
        this.hasFirstPart = false;
        this.hasSecondPart = false;
        this.hasThirdPart = false;
        this.hasFourthPart = false;

        allAttractions = new ArrayList<>();
        this.firstStart = true;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getCode4() {
        return code4;
    }

    public void setCode4(String code4) {
        this.code4 = code4;
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

    public boolean isHasThirdPart() {
        return hasThirdPart;
    }

    public void setHasThirdPart(boolean hasThirdPart) {
        this.hasThirdPart = hasThirdPart;
    }

    public boolean isHasFourthPart() {
        return hasFourthPart;
    }

    public void setHasFourthPart(boolean hasFourthPart) {
        this.hasFourthPart = hasFourthPart;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<AttrationInformation> getAllAttractions() {
        return allAttractions;
    }

    public void setAllAttractions(ArrayList<AttrationInformation> allAttractions) {
        this.allAttractions = allAttractions;
    }

    public boolean isFirstStart() {
        return firstStart;
    }

    public void setFirstStart(boolean firstStart) {
        this.firstStart = firstStart;
    }
}
