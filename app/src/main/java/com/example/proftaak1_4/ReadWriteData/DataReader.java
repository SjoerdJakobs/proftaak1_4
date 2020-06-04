package com.example.proftaak1_4.ReadWriteData;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DataReader {

    private SavedData savedData;
    private DataWriter dataWriter;
    private Context context;

    public DataReader(Context context)
    {
        savedData = SavedData.INSTANCE;
        this.context = context;
    }

    public void Load() throws IOException, ClassNotFoundException
    {
        ReadFile();
    }

    public void ReadFile() throws IOException, ClassNotFoundException
    {
        File path = context.getFilesDir();
        File file = new File(path, "AndroidSaveFile");

        if (!file.exists())
        {
            dataWriter = savedData.getDataWriter();
            dataWriter.WriteToFile();
            if(!file.exists())
            {
                System.out.println("FILE NOT FOUND AND UNABLE TO BE CREATED, "+this.getClass());
            }
        }
        else if(!file.isFile())
        {
            if(!file.delete())
            {
                System.out.println("INCORRECT FILE FOUND AND UNABLE TO CREATE A NEW FILE, "+this.getClass());
            }
            dataWriter = savedData.getDataWriter();
            dataWriter.WriteToFile();
        }

        if(!file.canRead())
        {
            System.out.println("UNABLE TO READ FILE, "+this.getClass());
        }

        FileInputStream fis   = context.openFileInput("AndroidSaveFile");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object readCase;

        SessionData sessionData = new SessionData();
        /*
        ArrayList<MyData> myDatas = savedData.getMyData();
        */

        do {
            readCase = (Object) ois.readObject();
            if (readCase != null) {
                if(readCase instanceof SessionData)
                {
                    sessionData = (SessionData) readCase;
                }
                /*else if(readCase instanceof MyData)
                {
                    myDatas.add((MyData) readCase);
                }*/
                else
                {
                    System.out.println("LOADING AN UNKNOWN OBJECT, CLASS: DataReader");
                }
            }
        } while (readCase != null);
        ois.close();

        savedData.setSessionData(sessionData);
        /*
        savedData.setMyData(myDatas);
        */
    }
}