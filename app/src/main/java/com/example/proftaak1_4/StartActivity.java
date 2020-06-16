package com.example.proftaak1_4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proftaak1_4.ReadWriteData.SavedData;

public class StartActivity extends AppCompatActivity {

    private SavedData data = SavedData.INSTANCE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen_activity);

        saveAndLoad();

        if(data.getSessionData().isFirstStart()){
            data.getSessionData().getAllAttractions().add(new AttractionInformation("Cobra", "cobra", R.drawable.cobra));
            data.getSessionData().getAllAttractions().add(new AttractionInformation("Fabel Woud", "fabelwoud", R.drawable.the_tree));
            data.getSessionData().setFirstStart(false);
        }

    }

    private void saveAndLoad()
    {
        Context context = getApplicationContext();
        SavedData savedData = SavedData.INSTANCE;
        savedData.Setup(context);
        savedData.Load();

        /* test code
        System.out.println(savedData.getSessionData().getUserName());

        if(savedData.getSessionData().getUserName().equals("yeee4"))
        {
            System.out.println("succes");
        }
        else
        {
            savedData.getSessionData().setUserName("yeee4");
            savedData.Save();
        }*/
    }

    public void StartTheApp(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
