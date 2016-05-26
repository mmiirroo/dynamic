
package me.moreshare.platform;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dalvik.system.DexClassLoader;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

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
        loadApk(paramBundle, dexPath, dexOutPath);
    }

    public void loadApk(Bundle bundle, String dexpath, String dexoutpppath) {
        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader localDexClassLoader = new DexClassLoader(dexpath, dexoutpppath, null,
                localClassLoader);

        try {
            PackageInfo localObject = getPackageManager().getPackageArchiveInfo(dexpath, 1);
            if (localObject.activities != null && localObject.activities.length > 0) {
                String activityName = localObject.activities[0].name;
                Log.d(TAG, "activityName = " + activityName);

                Class localClass = localDexClassLoader.loadClass(activityName);
                Constructor localConstructor = localClass.getConstructor(new Class[] {});
                Object instance = localConstructor.newInstance(new Object[] {});
                Log.d(TAG, "instance = " + instance);

                Method localMethodSetActivity = localClass.getMethod("setActivity", new Class[] {
                        Activity.class
                });
                localMethodSetActivity.setAccessible(true);
                localMethodSetActivity.invoke(instance, new Object[] {
                        this
                });

                Method methodCreate = localClass.getDeclaredMethod("onCreate", new Class[] {
                        Bundle.class
                });
                methodCreate.setAccessible(true);
                methodCreate.invoke(instance, new Object[] {
                        bundle
                });
                return;
            }
        } catch (Exception e) {
            Log.e(TAG, "exception in loadApk", e);
        }

    }
}
