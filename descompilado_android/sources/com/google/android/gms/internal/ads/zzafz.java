package com.google.android.gms.internal.ads;

import android.webkit.JavascriptInterface;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzafz {
    private final zzagg zzcyo;

    private zzafz(zzagg zzaggVar) {
        this.zzcyo = zzaggVar;
    }

    @JavascriptInterface
    public final void notify(@Nullable String str) {
        this.zzcyo.zzcx(str);
    }
}
