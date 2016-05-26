
package me.moreshare.gamea;

import android.app.Activity;
import android.os.Bundle;

public class ActivityGameA extends Activity {
    private static final String TAG = ActivityGameA.class.getSimpleName();
    private Activity otherActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isDynamicLoaded = false;
        if (null != savedInstanceState) {
            isDynamicLoaded = savedInstanceState.getBoolean("KEY_START_FROM_OTHER_ACTIVITY", false);
            if (isDynamicLoaded) {
                otherActivity.setContentView(new TBSurfaceView(otherActivity));
            }
        }
    }
}
