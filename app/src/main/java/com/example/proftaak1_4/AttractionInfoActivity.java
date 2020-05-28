package com.example.proftaak1_4;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AttractionInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attractie_info);

        setOnPageInformation();
    }

    private void setOnPageInformation() {
        ImageView pageDivider = findViewById(R.id.page_separator_image);
        ImageView attractionCover = findViewById(R.id.attraction_cover_image);

        pageDivider.setImageResource(R.drawable.divider);
        attractionCover.setImageResource(R.drawable.cobra);
    }
}
