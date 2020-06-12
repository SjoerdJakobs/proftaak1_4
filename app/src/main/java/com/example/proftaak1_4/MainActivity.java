package com.example.proftaak1_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
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


import com.example.proftaak1_4.ReadWriteData.SavedData;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.nio.channels.ScatteringByteChannel;

public class MainActivity extends AppCompatActivity {
    Dialog mydialog;
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
    }

    public void setLayoutForIcon(BottomNavigationMenuView menuView, int i, int id){
        final View iconView = menuView.getChildAt(i).findViewById(id);
        final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // set your height here
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, displayMetrics);
        // set your width here
        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, displayMetrics);
        iconView.setLayoutParams(layoutParams);
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


}
