package com.example.avengers;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Main8Activity extends AppCompatActivity {
  SQLiteDatabase db;
  EditText edt;
  ImageView option;
  ConstraintLayout hello;
  ImageButton a,b,c,d,e,f;
  WebView webView;
  ImageButton btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        edt=findViewById(R.id.edt);
        a=findViewById(R.id.a);
        b=findViewById(R.id.b);
        c=findViewById(R.id.c);
        d=findViewById(R.id.d);
        e=findViewById(R.id.e);
        f=findViewById(R.id.f);
        webView=findViewById(R.id.webView);
        btn1=findViewById(R.id.btn1);
        hello=findViewById(R.id.hello);
        option=findViewById(R.id.option);
        getSupportActionBar().hide();
        db=openOrCreateDatabase("myAvg",MODE_PRIVATE,null);
        registerForContextMenu(hello);
        webView.getSettings( ).setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("https://"+"m.dailyhunt.in/news/india/hindi");
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(Main8Activity.this,v);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String i1=""+item;
                        if(item.getItemId()==R.id.Incog)
                        {
                            Intent i=new Intent(Main8Activity.this,Main7Activity.class);
                            startActivity(i);
                        }
                        else if(item.getItemId()==R.id.history)
                        {
                            Intent i=new Intent(Main8Activity.this,Main6Activity.class);
                            startActivity(i);
                        }
                        return true;
                    }
                });
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                String S="www.youtube.com";
                i.putExtra("name",S);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                String S="www.wikipedia.com";
                i.putExtra("name",S);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                String S="www.flipkart.com";
                i.putExtra("name",S);
                startActivity(i);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                String S="www.facebook.com";
                i.putExtra("name",S);
                startActivity(i);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                String S="www.irctc.com";
                i.putExtra("name",S);
                startActivity(i);
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                String S="www.amazon.in";
                i.putExtra("name",S);
                startActivity(i);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                String S=edt.getText().toString();
                i.putExtra("name",S);
                startActivity(i);

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(android.R.drawable.star_on);
        menu.add(0,v.getId(),0,"Delete History");
        menu.add(0,v.getId(),0,"Incognito mode");
        menu.add(0,v.getId(),0,"History");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {


        if(item.getTitle()=="Delete History")
        {

            if (!db.isOpen()) {
                db = openOrCreateDatabase("myAvg", MODE_PRIVATE, null);
                Toast.makeText(this, ""+"History Deleted", Toast.LENGTH_SHORT).show();
            }


            db.execSQL("delete from tab_info");
            //db.execSQL("delete from  tab_info where Name='Abhishek'");
            db.close();}
        else if(item.getTitle()=="Incognito mode")
        {
            Intent i=new Intent(Main8Activity.this,Main7Activity.class);
            startActivity(i);
        }
        else
        {
            Intent i=new Intent(Main8Activity.this,Main6Activity.class);
            startActivity(i);
        }
        return super.onContextItemSelected(item);
    }
}
