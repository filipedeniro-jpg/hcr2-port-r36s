package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(19)
public class zzaue extends zzauf {
    @Override // com.google.android.gms.internal.ads.zzauf, com.google.android.gms.internal.ads.zzaub
    public final boolean isAttachedToWindow(View view) {
        return view.isAttachedToWindow();
    }

    @Override // com.google.android.gms.internal.ads.zzaub
    public final ViewGroup.LayoutParams zzvn() {
        return new ViewGroup.LayoutParams(-1, -1);
    }
}
