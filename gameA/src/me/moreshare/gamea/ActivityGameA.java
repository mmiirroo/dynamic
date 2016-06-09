
package me.moreshare.gamea;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ActivityGameA extends Activity {
    private static final String TAG = ActivityGameA.class.getSimpleName();
    private Activity otherActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean isDynamicLoaded = false;
        if (null != savedInstanceState) {
            isDynamicLoaded = savedInstanceState.getBoolean("KEY_START_FROM_OTHER_ACTIVITY", false);
            if (isDynamicLoaded) {
                otherActivity.setContentView(new TBSurfaceView(otherActivity));
            } else {
                super.onCreate(savedInstanceState);
                // setContentView(R.layout.main);
                setContentView(new TBSurfaceView(this));
            }

        }
    }

    public void setActivity(Activity paramActivity) {
        Log.d(TAG, "setActivity..." + paramActivity);
        otherActivity = paramActivity;
    }
}
