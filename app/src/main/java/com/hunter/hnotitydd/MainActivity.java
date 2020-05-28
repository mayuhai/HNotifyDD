package com.hunter.hnotitydd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hunter.anotifydd.PerformanceReportManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PerformanceReportManager.getInstance().notifyToDD(
                "Network",
                "String",
                "https://oapi.dingtalk.com/robot/send?access_token=acfe1bc87101d985c5e2f491254118a076e80369d64f4e001bd48e5d87210172",
                MainActivity.this);
    }
}
