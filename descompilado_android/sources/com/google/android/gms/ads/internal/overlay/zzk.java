package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzbbc;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class zzk {
    public final int index;
    public final ViewGroup parent;
    public final ViewGroup.LayoutParams zzdhi;
    public final Context zzlk;

    public zzk(zzbbc zzbbcVar) throws zzi {
        this.zzdhi = zzbbcVar.getLayoutParams();
        ViewParent parent = zzbbcVar.getParent();
        this.zzlk = zzbbcVar.zzyt();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new zzi("Could not get the parent of the WebView for an overlay.");
        }
        this.parent = (ViewGroup) parent;
        this.index = this.parent.indexOfChild(zzbbcVar.getView());
        this.parent.removeView(zzbbcVar.getView());
        zzbbcVar.zzaq(true);
    }
}
