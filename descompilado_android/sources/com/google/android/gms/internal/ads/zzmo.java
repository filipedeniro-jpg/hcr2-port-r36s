package com.google.android.gms.internal.ads;

import android.text.TextUtils;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzmo {
    public final int viewportHeight;
    public final int viewportWidth;
    public final String zzbdf;
    public final String zzbdg;
    public final boolean zzbdh;
    public final boolean zzbdi;
    public final int zzbdj;
    public final int zzbdk;
    public final int zzbdl;
    public final boolean zzbdm;
    public final boolean zzbdn;
    public final boolean zzbdo;

    public zzmo() {
        this(null, null, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
    }

    private zzmo(String str, String str2, boolean z, boolean z2, int i, int i2, int i3, boolean z3, boolean z4, int i4, int i5, boolean z5) {
        this.zzbdf = null;
        this.zzbdg = null;
        this.zzbdh = false;
        this.zzbdi = true;
        this.zzbdj = Integer.MAX_VALUE;
        this.zzbdk = Integer.MAX_VALUE;
        this.zzbdl = Integer.MAX_VALUE;
        this.zzbdm = true;
        this.zzbdn = true;
        this.viewportWidth = Integer.MAX_VALUE;
        this.viewportHeight = Integer.MAX_VALUE;
        this.zzbdo = true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzmo zzmoVar = (zzmo) obj;
            if (this.zzbdi == zzmoVar.zzbdi && this.zzbdj == zzmoVar.zzbdj && this.zzbdk == zzmoVar.zzbdk && this.zzbdm == zzmoVar.zzbdm && this.zzbdn == zzmoVar.zzbdn && this.zzbdo == zzmoVar.zzbdo && this.viewportWidth == zzmoVar.viewportWidth && this.viewportHeight == zzmoVar.viewportHeight && this.zzbdl == zzmoVar.zzbdl && TextUtils.equals(null, null) && TextUtils.equals(null, null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = null;
        return (((((((((((((((((((str.hashCode() * 31) + str.hashCode()) * 31 * 31) + (this.zzbdi ? 1 : 0)) * 31) + this.zzbdj) * 31) + this.zzbdk) * 31) + this.zzbdl) * 31) + (this.zzbdm ? 1 : 0)) * 31) + (this.zzbdn ? 1 : 0)) * 31) + (this.zzbdo ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight;
    }
}
