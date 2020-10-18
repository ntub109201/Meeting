package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AnythingTestActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private boolean abc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anything_test);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (abc){
                    floatingActionButton.animate().rotation(floatingActionButton.getRotation()+135).start();
                    abc = false;
                }else {
                    floatingActionButton.animate().rotation(floatingActionButton.getRotation()-135).start();
                    abc = true;
                }

            }
        });

    }
}
