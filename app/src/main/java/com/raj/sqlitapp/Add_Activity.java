package com.raj.sqlitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Activity extends AppCompatActivity {
    EditText edit_title,edit_desc;
    Button btn_add;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);

        btn_add=findViewById(R.id.btn_add);
        edit_title=findViewById(R.id.edit_title);
        edit_desc=findViewById(R.id.edit_desc);

        dbManager=new DBManager(this);
        dbManager.open();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=edit_title.getText().toString();
                String desc=edit_desc.getText().toString();
                dbManager.insert(title,desc);

                Intent main=new Intent(Add_Activity.this,CountryListActivity.class);
                main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);

            }
        });

    }
}