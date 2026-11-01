package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcng implements zzcra<zzcnh> {
    private final zzatb zzblz;
    private final zzcvk zzffn;
    private final zzcra<zzcrh> zzgdo;
    private final Context zzlk;

    public zzcng(zzcon<zzcrh> zzconVar, zzcvk zzcvkVar, Context context, zzatb zzatbVar) {
        this.zzgdo = zzconVar;
        this.zzffn = zzcvkVar;
        this.zzlk = context;
        this.zzblz = zzatbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcnh> zzalr() {
        return zzdcf.zzb(this.zzgdo.zzalr(), new zzczs(this) { // from class: com.google.android.gms.internal.ads.zzcnj
            private final zzcng zzgds;

            {
                this.zzgds = this;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj) {
                return this.zzgds.zza((zzcrh) obj);
            }
        }, zzawx.zzdwb);
    }

    final /* synthetic */ zzcnh zza(zzcrh zzcrhVar) {
        String str;
        boolean z;
        String strZzve;
        float f;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        DisplayMetrics displayMetrics;
        zztw zztwVar = this.zzffn.zzbli;
        if (zztwVar.zzccf == null) {
            str = zztwVar.zzabd;
            z = zztwVar.zzccg;
        } else {
            str = null;
            boolean z2 = false;
            boolean z3 = false;
            z = false;
            for (zztw zztwVar2 : zztwVar.zzccf) {
                if (!zztwVar2.zzccg && !z2) {
                    str = zztwVar2.zzabd;
                    z2 = true;
                }
                if (zztwVar2.zzccg && !z3) {
                    z3 = true;
                    z = true;
                }
                if (z2 && z3) {
                    break;
                }
            }
        }
        Resources resources = this.zzlk.getResources();
        if (resources == null || (displayMetrics = resources.getDisplayMetrics()) == null) {
            strZzve = null;
            f = 0.0f;
            i = 0;
            i2 = 0;
        } else {
            float f2 = displayMetrics.density;
            int i6 = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
            strZzve = this.zzblz.zzuj().zzve();
            i = i6;
            f = f2;
        }
        StringBuilder sb = new StringBuilder();
        if (zztwVar.zzccf != null) {
            boolean z4 = false;
            for (zztw zztwVar3 : zztwVar.zzccf) {
                if (zztwVar3.zzccg) {
                    z4 = true;
                } else {
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    if (zztwVar3.width == -1 && f != 0.0f) {
                        i4 = (int) (zztwVar3.widthPixels / f);
                    } else {
                        i4 = zztwVar3.width;
                    }
                    sb.append(i4);
                    sb.append("x");
                    if (zztwVar3.height == -2 && f != 0.0f) {
                        i5 = (int) (zztwVar3.heightPixels / f);
                    } else {
                        i5 = zztwVar3.height;
                    }
                    sb.append(i5);
                }
            }
            if (z4) {
                if (sb.length() != 0) {
                    i3 = 0;
                    sb.insert(0, "|");
                } else {
                    i3 = 0;
                }
                sb.insert(i3, "320x50");
            }
        }
        return new zzcnh(zztwVar, str, z, sb.toString(), f, i, i2, strZzve);
    }
}
