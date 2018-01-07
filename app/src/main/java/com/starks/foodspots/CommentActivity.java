package com.starks.foodspots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CommentActivity extends AppCompatActivity {
    Spinner spinPrif;
    EditText editCom;
    Button btnSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        onViews();
    }

    void onViews(){
        spinPrif=(Spinner) findViewById(R.id.spinnerPreference);
        editCom=(EditText) findViewById(R.id.editComment);
        btnSub=(Button) findViewById(R.id.btnSubmit);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
