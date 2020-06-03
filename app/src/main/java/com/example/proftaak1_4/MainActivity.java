package com.example.proftaak1_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Dialog mydialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plattegrond);

        mydialog = new Dialog(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setFocusable(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        //ImageButton information_button = findViewById(R.id.imageButton);
        //Button X_close = findViewById(R.id.button);
        /*
        information_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Popup.class));
            }
        });
*/
        //X_close.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //startActivity(new Intent());
            //}
        //});
    }

    public void ShowPopUp(View v){
        mydialog.setContentView(R.layout.popup);
        TextView txtclose= mydialog.findViewById(R.id.textView);
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
