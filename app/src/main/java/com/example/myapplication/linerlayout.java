package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class linerlayout extends AppCompatActivity {
 public TextView textView;
 public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linerlayout);

        textView = findViewById(R.id.viwo);
        Intent intent = getIntent();
        Bundle bundle = ((Intent) intent).getExtras();
        if (bundle != null) {
            int value1 = bundle.getInt("nam");
            textView.setText(value1 + "");
        }
        button = (Button) findViewById(R.id.quaylai);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hi= new Intent(getApplicationContext(), linerlayout2.class);
                startActivity(hi);

            }
        });
    }

}
