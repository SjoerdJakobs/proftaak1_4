package com.example.proftaak1_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AttractionInfoActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT = "all/information";

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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        AttractionInformation information = null;
        if (bundle != null) {
            information = (AttractionInformation) bundle.getSerializable(EXTRA_OBJECT);

        }

        if (information != null) {
            TextView textView = findViewById(R.id.attraction_description);
            textView.setText(information.getText());
            attractionCover.setImageResource(information.getDrawable());

        }
    }

    public void switchToAllAtractions(View v) {
        Intent intent = new Intent(this, AllAtractionActivity.class);

        if (intent != null)
            startActivity(intent);
    }
}
