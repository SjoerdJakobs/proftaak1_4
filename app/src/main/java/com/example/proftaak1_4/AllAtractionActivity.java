package com.example.proftaak1_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.proftaak1_4.ReadWriteData.SavedData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class AllAtractionActivity extends AppCompatActivity implements onItemClickListener {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<AttrationInformation> allAttractions;

    SavedData data = SavedData.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_atraction);

        allAttractions = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this, data.getSessionData().getAllAttractions(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(data.getSessionData().isFirstStart()){
            data.getSessionData().getAllAttractions().add(new AttrationInformation("Cobra", "cobra"));
            data.getSessionData().getAllAttractions().add(new AttrationInformation("Fabel Woud", "fabelwoud"));
            adapter.notifyDataSetChanged();
            data.getSessionData().setFirstStart(false);
            System.out.println("HELLO LOADING ");
        }



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setFocusable(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

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


    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, AttractionInfoActivity.class);
        AttrationInformation information = data.getSessionData().getAllAttractions().get(position);

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
