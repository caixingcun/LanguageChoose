package com.handom.webviewdebug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.caixingcun.languagechoose.util.LanguageUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.upDataLocate(newBase));
    }

    private RadioButton rbChinese;
    private RadioButton rbTChinese;
    private RadioButton rbEnglish;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        rbChinese = (RadioButton) findViewById(R.id.rb_chinese);
        rbTChinese = (RadioButton) findViewById(R.id.rb_t_chinese);
        rbEnglish = (RadioButton) findViewById(R.id.rb_english);
        findViewById(R.id.btn_conform).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rbChinese.isChecked()) {
                    LanguageUtil.switchLanguage(mActivity, App.languages[0]);
                    startActivity(new Intent(mActivity,MainActivity.class));
                    finish();
                    return;
                }
                if (rbTChinese.isChecked()) {
                    LanguageUtil.switchLanguage(mActivity, App.languages[1]);
                    startActivity(new Intent(mActivity,MainActivity.class));
                    finish();
                    return;
                }
                if (rbEnglish.isChecked()) {
                    LanguageUtil.switchLanguage(mActivity, App.languages[3]);
                    startActivity(new Intent(mActivity,MainActivity.class));
                    finish();
                    return;
                }

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
