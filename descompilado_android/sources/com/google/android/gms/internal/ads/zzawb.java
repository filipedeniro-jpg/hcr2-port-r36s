package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzawb implements Callable<String> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ Context zzduv;

    zzawb(zzavz zzavzVar, Context context, Context context2) {
        this.zzduv = context;
        this.val$context = context2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        SharedPreferences sharedPreferences;
        boolean z = false;
        if (this.zzduv != null) {
            zzatm.zzdy("Attempting to read user agent from Google Play Services.");
            sharedPreferences = this.zzduv.getSharedPreferences("admob_user_agent", 0);
        } else {
            zzatm.zzdy("Attempting to read user agent from local cache.");
            sharedPreferences = this.val$context.getSharedPreferences("admob_user_agent", 0);
            z = true;
        }
        String string = sharedPreferences.getString("user_agent", "");
        if (TextUtils.isEmpty(string)) {
            zzatm.zzdy("Reading user agent from WebSettings");
            string = WebSettings.getDefaultUserAgent(this.val$context);
            if (z) {
                sharedPreferences.edit().putString("user_agent", string).apply();
                zzatm.zzdy("Persisting user agent.");
            }
        }
        return string;
    }
}
