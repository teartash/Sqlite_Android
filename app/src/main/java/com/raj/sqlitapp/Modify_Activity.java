package com.raj.sqlitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modify_Activity extends AppCompatActivity {
    EditText edit_title,edit_desc;
    Button btn_update,btn_delete;
    DBManager dbManager;
    private Long _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_);

        dbManager=new DBManager(this);
        dbManager.open();

        edit_title=findViewById(R.id.edit_title);
        edit_desc=findViewById(R.id.edit_desc);
        btn_update=findViewById(R.id.btn_update);
        btn_delete=findViewById(R.id.btn_delete);

        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        final String title=intent.getStringExtra("title");
        String desc=intent.getStringExtra("desc");

        _id=Long.parseLong(id);
        edit_title.setText(title);
        edit_desc.setText(desc);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=edit_title.getText().toString();
                String desc=edit_desc.getText().toString();

                dbManager.update(_id,title,desc);
                 returnHome();

            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbManager.delete(_id);
                returnHome();


            }
        });
    }
    public void returnHome(){
        Intent main=new Intent(getApplicationContext(),CountryListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);

    };
}