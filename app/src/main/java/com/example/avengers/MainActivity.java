package com.example.avengers;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    ImageButton mag;
    ImageView option;

  //  private TextView mTextMessage;
    LinearLayout hello;
    EditText auto;
    String S="";
    WebView webView;
    SQLiteDatabase db;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      getSupportActionBar().hide();
        mag=findViewById(R.id.mag);
        auto=findViewById(R.id.auto);
        option=findViewById(R.id.option);
        webView=findViewById(R.id.webView);
        hello=findViewById(R.id.hello);


        Intent intent=getIntent();
       S=intent.getStringExtra("name");
        hello=findViewById(R.id.hello);
        registerForContextMenu(hello);
        auto.setText(S);
        db=openOrCreateDatabase("myAvg",MODE_PRIVATE,null);
        db.execSQL("create table if not exists tab_info(Site varchar(20))");
       // setContentView(R.layout.activity_main4);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(MainActivity.this,v);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String i1=""+item;
                        if(item.getItemId()==R.id.Incog)
                        {
                            Intent i=new Intent(MainActivity.this,Main7Activity.class);
                            startActivity(i);
                        }
                        else if(item.getItemId()==R.id.history)
                        {
                            Intent i=new Intent(MainActivity.this,Main6Activity.class);
                            startActivity(i);
                        }
                        return false;
                    }
                });
            }
        });
     //   navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         mag.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                  S=auto.getText().toString();
                   webView.getSettings( ).setJavaScriptEnabled(true);
                   webView.getSettings().setUseWideViewPort(true);
                   webView.loadUrl("https://"+S);
                   webView.setWebViewClient(new WebViewClient()
                   {
                       @Override
                       public boolean shouldOverrideUrlLoading(WebView view, String url) {
                           view.loadUrl(url);
                           return true;
                       }
                   });
                 if (!db.isOpen()) {
                     db = openOrCreateDatabase("myAvg", MODE_PRIVATE, null);
                 }

                    if(S.length()>0) {
                        db.execSQL("insert into tab_info values('" + auto.getText().toString() + "')");
                    }
                 db.close();

             }
         });


    }


    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            finish();
        }
    }


    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
      //  Toast.makeText(this, ""+"abbb", Toast.LENGTH_SHORT).show();
        S=auto.getText().toString();
        webView.getSettings( ).setJavaScriptEnabled(true);
        if(S.length()!=0) {
            webView.getSettings().setUseWideViewPort(true);
            webView.loadUrl("https://"+S);
        }
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        if (!db.isOpen()) {
            db = openOrCreateDatabase("myAvg", MODE_PRIVATE, null);
        }
        if(S.length()!=0) {
            db.execSQL("insert into tab_info values('" + auto.getText().toString() + "')");
        }

        db.close();
    }
}
