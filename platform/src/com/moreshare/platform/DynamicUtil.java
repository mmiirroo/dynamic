package com.moreshare.platform;

import java.lang.reflect.Constructor;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import dalvik.system.DexClassLoader;

public class DynamicUtil {

    private static final String TAG = DynamicUtil.class.getSimpleName();

    public static void loadApk(Context context, Bundle bundle, String dexpath, String dexoutpppath) {
        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader localDexClassLoader = new DexClassLoader(dexpath, dexoutpppath, null,
                localClassLoader);


        PackageInfo localObject = context.getPackageManager().getPackageArchiveInfo(dexpath, 1);
        if (localObject.activities != null && localObject.activities.length > 0) {
            String activityName = localObject.activities[0].name;
            Log.d(TAG, "activityName = " + activityName);


        Class localClass = localDexClassLoader.loadClass(activityName);
        Constructor localConstructor = localClass.getConstructor(new Class[] {});

        }
    }
}
