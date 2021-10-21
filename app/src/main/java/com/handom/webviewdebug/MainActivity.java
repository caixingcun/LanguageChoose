package com.handom.webviewdebug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.caixingcun.languagechoose.LanguageDelegate;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageDelegate.getInstance().upDataLocate(newBase));
    }

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        findViewById(R.id.rb_chinese).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageDelegate.getInstance().switchLanguage(mActivity, App.languages[0]);
                recreate();
            }
        });
        findViewById(R.id.rb_t_chinese).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageDelegate.getInstance().switchLanguage(mActivity, App.languages[1]);
                recreate();
            }
        });
        findViewById(R.id.rb_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageDelegate.getInstance().switchLanguage(mActivity, App.languages[3]);
                recreate();
            }
        });

        final EditText et = findViewById(R.id.et);
        final RadioButton rb = findViewById(R.id.rb1);
        findViewById(R.id.btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean fullScreen = rb.isChecked();
                        Intent intent;
                        if (fullScreen) {
                            intent = new Intent(MainActivity.this, FullScreenWebViewActivity.class);
                        } else {
                            intent = new Intent(MainActivity.this, WebViewActivity.class);
                        }
                        intent.putExtra("url", et.getText().toString().trim());
                        startActivity(intent);
                    }
                });
    }
}
