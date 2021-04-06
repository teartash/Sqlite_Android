package com.raj.sqlitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CountryListActivity extends AppCompatActivity {
    private SimpleCursorAdapter adapter;
    private ListView listView;
    private DBManager dbManager;

    final  String[] from=new String[]{DatabaseHelper._ID,DatabaseHelper.SUBJECT,DatabaseHelper.DESC};
    final int[] to=new int[]{R.id.id,R.id.title,R.id.desc};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        dbManager=new DBManager(this);
        dbManager.open();
        Cursor cursor=dbManager.fetch();
        listView=findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.txt_empty));

        adapter=new SimpleCursorAdapter(this,R.layout.activity_view_record,cursor,from,to,0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idtextview=view.findViewById(R.id.id);
                TextView titletextview=view.findViewById(R.id.title);
                TextView desctextview=view.findViewById(R.id.desc);


                String id=idtextview.getText().toString();
                String title=titletextview.getText().toString();
                String desc=desctextview.getText().toString();

                Intent  modify_intent=new Intent(getApplicationContext(),Modify_Activity.class);
                modify_intent.putExtra("id",id);
                modify_intent.putExtra("title",title);
                modify_intent.putExtra("desc",desc);
                startActivity(modify_intent);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.add_record){
            startActivity(new Intent(this,Add_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}