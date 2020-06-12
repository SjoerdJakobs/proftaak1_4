package com.example.proftaak1_4.ReadWriteData;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;

public class DataWriter {

    private SavedData savedData;
    private Context context;

    public DataWriter(Context context)
    {
        savedData = SavedData.INSTANCE;
        this.context = context;
    }

    public void Save() throws IOException, ClassNotFoundException
    {
        WriteToFile();
    }

    public void WriteToFile() throws IOException, ClassNotFoundException
    {
        File path = context.getFilesDir();
        File file = new File(path, "AndroidSaveFile");
        if(file.exists())
        {
            if(!file.delete())
            {
                System.out.println("UNABLE TO DELETE OLD FILE, "+this.getClass());
            }
        }

        FileOutputStream fos   = context.openFileOutput("AndroidSaveFile", Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        if(!file.canWrite())
        {
            System.out.println("UNABLE TO WRITE TO FILE, "+this.getClass());
        }


        SessionData sessionData = savedData.getSessionData();
        //ArrayList<MyData> myDatas = savedData.getMyData();

        oos.writeObject(sessionData);

        /*for(MyData md : myDatas)
        {
            oos.writeObject(md);
        }*/

        //this null object lets the data writer know that it is the end of the save file
        oos.writeObject(null);
        oos.close();
    }
}