package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class MN implements Runnable {
    public final /* synthetic */ Context B;

    public MN(Context context) {
        this.B = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int iE;
        if (MP.C == MO.INITIALIZED) {
            return;
        }
        SharedPreferences sharedPreferences = this.B.getSharedPreferences(I6.C("FBAdPrefs", this.B), 0);
        int i = sharedPreferences.getInt("AppMinSdkVersion", -1);
        if (i == -1) {
            if (Build.VERSION.SDK_INT < 24) {
                iE = MP.E(this.B);
            } else {
                iE = MP.F(this.B);
            }
            int unused = MP.B = iE;
            sharedPreferences.edit().putInt("AppMinSdkVersion", iE).commit();
            MP.C = MO.INITIALIZED;
            return;
        }
        int unused2 = MP.B = i;
        MP.C = MO.INITIALIZED;
    }
}
