package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.ironsource.sdk.constants.Constants;
import java.io.File;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0688Lz {
    private C0688Lz() {
    }

    public static File B(Context context) {
        File cacheDir = C(context, true);
        return new File(cacheDir, "video-cache");
    }

    private static File C(Context context, boolean preferExternal) {
        String externalStorageState;
        File cacheDir = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        }
        if (preferExternal && "mounted".equals(externalStorageState)) {
            cacheDir = D(context);
        }
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        if (cacheDir == null) {
            String str = "/data/data/" + context.getPackageName() + "/cache/";
            Log.w("ProxyCache", "Can't define system cache directory! '" + str + "%s' will be used.");
            return new File(str);
        }
        return cacheDir;
    }

    private static File D(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), Constants.JAVASCRIPT_INTERFACE_NAME), "data");
        File file = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!file.exists() && !file.mkdirs()) {
            Log.w("ProxyCache", "Unable to create external cache directory");
            return null;
        }
        return file;
    }
}
