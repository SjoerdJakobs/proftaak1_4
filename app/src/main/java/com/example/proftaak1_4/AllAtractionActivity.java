package com.example.proftaak1_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AllAtractionActivity extends AppCompatActivity implements onItemClickListener {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<AttrationInformation> allAttractions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_atraction);

        allAttractions = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this, allAttractions, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allAttractions.add(new AttrationInformation("Cobra", "cobra"));
        allAttractions.add(new AttrationInformation("Fabel Woud", "fabelwoud"));
        adapter.notifyDataSetChanged();


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

        Intent intent = new Intent(this, DetailAttractionActivity.class);
        AttrationInformation information = this.allAttractions.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailAttractionActivity.EXTRA_OBJECT, information);

        intent.putExtras(bundle);
        startActivity(intent);
    }
}
