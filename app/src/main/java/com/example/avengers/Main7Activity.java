package com.example.avengers;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Main7Activity extends AppCompatActivity {
  WebView webView;
   LinearLayout hello;
   EditText auto;
   ImageButton mag;
   String S;
   ImageView option;



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        webView=findViewById(R.id.webView);
        hello=findViewById(R.id.hello);
         option=findViewById(R.id.option);
        mag=findViewById(R.id.mag);
        getSupportActionBar().hide();
        auto=findViewById(R.id.auto);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup=new PopupMenu(Main7Activity.this,v);
                popup.getMenuInflater().inflate(R.menu.popup_menu1, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String i1=""+item;
                        if(item.getItemId()==R.id.history)
                        {
                            Intent i=new Intent(Main7Activity.this,Main6Activity.class);
                            startActivity(i);
                        }
                        else if(item.getItemId()==R.id.Incogoff)
                        {
                            Intent i=new Intent(Main7Activity.this,MainActivity.class);
                            startActivity(i);
                        }
                        return false;
                    }
                });
            }
        });

        Toast.makeText(this, ""+"Incognito Mode", Toast.LENGTH_SHORT).show();

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
            }
        });



    }


}
