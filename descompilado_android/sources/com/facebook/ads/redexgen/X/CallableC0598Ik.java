package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ik, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class CallableC0598Ik implements Callable<Boolean> {
    public final /* synthetic */ Context B;
    public final /* synthetic */ String C;
    public final /* synthetic */ SharedPreferences D;

    public CallableC0598Ik(Context context, SharedPreferences sharedPreferences, String str) {
        this.B = context;
        this.D = sharedPreferences;
        this.C = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Boolean call() throws Exception {
        String unused = C0599Il.G = C0599Il.F(this.B, this.B.getPackageName());
        this.D.edit().putString(this.C, C0599Il.G).apply();
        C0599Il.D.set(2);
        return true;
    }
}
