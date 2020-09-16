package com.example.avengers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main6Activity extends AppCompatActivity {



    ListView lstvw;
    SQLiteDatabase db;
    ArrayAdapter apt;
   // String[] nam = list.toArray(new String[list.size()]);

    int i=0;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Toast.makeText(this, ""+"History", Toast.LENGTH_SHORT).show();
        lstvw=findViewById(R.id.lstvw);
        // list.add("hello");
        db=openOrCreateDatabase("myAvg",MODE_PRIVATE,null);

        Cursor cursor=db.rawQuery("select * from tab_info",null);
        while(cursor.moveToNext())
        {  i++;
            list.add(cursor.getString(0));
        }
        Collections.reverse(list);
        apt=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,list);
        lstvw.setAdapter(apt);
    final    String[] nam = list.toArray(new String[list.size()]);
         lstvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                String str=nam[position];
                Toast.makeText(Main6Activity.this, ""+nam[position], Toast.LENGTH_SHORT).show();
              i.putExtra("name",nam[position]);
               startActivity(i);
            }
        });

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m13)
        {

            if (!db.isOpen()) {
                db = openOrCreateDatabase("myAvg", MODE_PRIVATE, null);
                Toast.makeText(this, ""+"History Deleted", Toast.LENGTH_SHORT).show();
            }


            db.execSQL("delete from tab_info");

            db.close();}
        else if(item.getItemId()==R.id.m1)
        {
            Intent i=new Intent(Main6Activity.this,Main7Activity.class);
            startActivity(i);
        }
        else
        {
            Intent i=new Intent(Main6Activity.this,Main6Activity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}