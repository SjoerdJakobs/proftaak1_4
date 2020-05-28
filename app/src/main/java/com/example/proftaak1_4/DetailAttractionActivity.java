package com.example.proftaak1_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.net.Inet4Address;

public class DetailAttractionActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT = "all/information";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_attraction);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        AttrationInformation information = null;
        if(bundle != null){
            information = (AttrationInformation) bundle.getSerializable(EXTRA_OBJECT);
        }

        if(information != null){
            TextView textView = findViewById(R.id.textView_detail);
            textView.setText(information.getText());
        }

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

            case R.id.map:
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.spell:
                intent = new Intent(this, AntiSpellActivity.class);
                break;
        }


        if(intent != null)
            startActivity(intent);
    }



}
