package com.vungle.warren.ui;

import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class VungleFlexViewActivity extends VungleActivity {
    private static final String TAG = "VungleFlexViewActivity";

    @Override // com.vungle.warren.ui.VungleActivity
    protected boolean canRotate() {
        boolean z = getApplication().getApplicationInfo().targetSdkVersion >= 27 && Build.VERSION.SDK_INT == 26;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("allow rotation = ");
        sb.append(!z);
        Log.d(str, sb.toString());
        return !z;
    }
}
