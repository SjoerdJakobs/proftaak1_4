package com.example.proftaak1_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.proftaak1_4.ReadWriteData.SavedData;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.nio.channels.ScatteringByteChannel;

public class MainActivity extends AppCompatActivity {
    Dialog mydialog;

    SavedData data = SavedData.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plattegrond);

        mydialog = new Dialog(this);

//        saveAndLoad();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setFocusable(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        MenuItem item = bottomNavigationView.getMenu().findItem(R.id.map);
        item.setChecked(true);

        ImageButton button1 = findViewById(R.id.imageButton2);
        ImageButton button2 = findViewById(R.id.imageButton5);

        AttrationInformation information1 = data.getSessionData().getAllAttractions().get(1);
        AttrationInformation information2 = data.getSessionData().getAllAttractions().get(0);

        if(information1.isUnlocked()) {
            button1.setColorFilter(Color.parseColor("#38FF00")); //GREEN
        } else{
            button1.setColorFilter(Color.parseColor("#68E1D7")); //BLUE
        }

        if(information2.isUnlocked()) {
            button2.setColorFilter(Color.parseColor("#38FF00"));
        }else{
            button2.setColorFilter(Color.parseColor("#68E1D7"));
        }

        Intent mqttService = new Intent(this, MQTTService.class);
        startService(mqttService);
    }


    public void ShowPopUp(View v) {
        mydialog.setContentView(R.layout.popup);
        TextView txtclose = mydialog.findViewById(R.id.textView);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }
        });
        mydialog.show();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switchTo(item.getItemId());
                    return true;
                }
            };

    private void switchTo(int navigation){

        Intent intent = null;
        switch(navigation){
            case R.id.attractions:
                 intent = new Intent(this, AllAtractionActivity.class);
                 break;

            case R.id.spell:
                intent = new Intent(this, AntiSpellActivity.class);
                break;
        }

        if(intent != null)
            startActivity(intent);
    }


        public void toDetailScreen(View v) {

        Intent intent = new Intent(this, AttractionInfoActivity.class);
        AttrationInformation information = data.getSessionData().getAllAttractions().get(1);

        if(information.isUnlocked()){
            Bundle bundle = new Bundle();
            bundle.putSerializable(DetailAttractionActivity.EXTRA_OBJECT, information);

            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Je hebt deze attractie nog niet vrijgespeeld/bevrijd!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void toDetailScreen2(View v) {

        Intent intent = new Intent(this, AttractionInfoActivity.class);
        AttrationInformation information = data.getSessionData().getAllAttractions().get(0);

        if(information.isUnlocked()){
            Bundle bundle = new Bundle();
            bundle.putSerializable(DetailAttractionActivity.EXTRA_OBJECT, information);

            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Je hebt deze attractie nog niet vrijgespeeld/bevrijd!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
}
