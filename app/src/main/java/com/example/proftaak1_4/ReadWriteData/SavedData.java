package com.example.proftaak1_4.ReadWriteData;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

//https://dzone.com/articles/java-singletons-using-enum
public enum SavedData {
    INSTANCE;
    private Context context;
    private DataReader dataReader;
    private DataWriter dataWriter;
    private boolean hasBeenSetup = false;

    //place stuff to be saved and loaded here
    private SessionData sessionData;

    public void Setup(Context context)
    {
        this.context = context;
        this.dataReader = new DataReader(context);
        this.dataWriter = new DataWriter(context);
        this.sessionData = new SessionData();
        this.hasBeenSetup = true;
    }

    public void Save()
    {
        if(hasBeenSetup) {
            try {
                dataWriter.Save();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("ERROR: call the Setup method first before saving");
        }
    }

    public void Load()
    {
        if(hasBeenSetup) {
            try {
                dataReader.Load();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("ERROR: call the Setup method first before loading");
        }
    }


    public DataReader getDataReader() {
        return dataReader;
    }

    public DataWriter getDataWriter() {
        return dataWriter;
    }

    public SessionData getSessionData() {
        return sessionData;
    }

    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }
}
