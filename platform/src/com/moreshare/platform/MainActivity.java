package com.moreshare.platform;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGameA = (Button) findViewById(R.id.button_gameA);
        buttonGameA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle paramBundle = new Bundle();
        paramBundle.putBoolean("KEY_START_FROM_OTHER_ACTIVITY", true);
        String dexPath = "/mnt/sdcard/gameA.apk";
        String dexOutPath = "/mnt/sdcard/";
        DynamicUtil.loadApk(this.getApplicationContext(), paramBundle, dexPath, dexOutPath);
    }

}
