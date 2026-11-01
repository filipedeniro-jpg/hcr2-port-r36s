package com.google.android.gms.internal.ads;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzzz extends zzaaj {
    private static final int zzcuz = Color.rgb(12, 174, 206);
    private static final int zzcva;
    private static final int zzcvb;
    private static final int zzcvc;
    private final int backgroundColor;
    private final int textColor;
    private final int textSize;
    private final String zzcvd;
    private final List<zzaae> zzcve = new ArrayList();
    private final List<zzaas> zzcvf = new ArrayList();
    private final int zzcvg;
    private final int zzcvh;
    private final boolean zzcvi;

    public zzzz(String str, List<zzaae> list, Integer num, Integer num2, Integer num3, int i, int i2, boolean z) {
        this.zzcvd = str;
        if (list != null) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                zzaae zzaaeVar = list.get(i3);
                this.zzcve.add(zzaaeVar);
                this.zzcvf.add(zzaaeVar);
            }
        }
        this.backgroundColor = num != null ? num.intValue() : zzcvb;
        this.textColor = num2 != null ? num2.intValue() : zzcvc;
        this.textSize = num3 != null ? num3.intValue() : 12;
        this.zzcvg = i;
        this.zzcvh = i2;
        this.zzcvi = z;
    }

    @Override // com.google.android.gms.internal.ads.zzaak
    public final String getText() {
        return this.zzcvd;
    }

    @Override // com.google.android.gms.internal.ads.zzaak
    public final List<zzaas> zzqa() {
        return this.zzcvf;
    }

    public final List<zzaae> zzqb() {
        return this.zzcve;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final int getTextSize() {
        return this.textSize;
    }

    public final int zzqc() {
        return this.zzcvg;
    }

    public final int zzqd() {
        return this.zzcvh;
    }

    static {
        int iRgb = Color.rgb(204, 204, 204);
        zzcva = iRgb;
        zzcvb = iRgb;
        zzcvc = zzcuz;
    }
}
