package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent != null) {
            String languageCode = intent.getStringExtra("language");
            if (languageCode != null && !languageCode.isEmpty()) {
                setAppLocale(languageCode);
                // Reload the activity to apply language change immediately
                recreate();
            }
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(getString(R.string.hello_world));
    }

    private void setAppLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }



    public void main_btn(View view) {
        switch (view.getId()){
            case R.id.btn_play:
                startActivity(new Intent(MainActivity.this , playActivity.class));
                break;
            case R.id.btn_setting:
                startActivity(new Intent(MainActivity.this , settingActivity.class));

                break;
            case R.id.btn_exit:
                this.finishAffinity();
                break;
        }
    }
}