package com.google.android.gms.internal.ads;

import android.graphics.Rect;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzpi {
    private final long timestamp;
    public final boolean zzbnr;
    private final boolean zzbnu;
    public final boolean zzbnv;
    public final Rect zzbnw;
    public final Rect zzbnx;
    public final Rect zzbny;
    public final boolean zzbnz;
    public final Rect zzboa;
    public final boolean zzbob;
    public final Rect zzboc;
    private final float zzbod;
    public final List<Rect> zzboe;
    public final int zzzk;

    public zzpi(long j, boolean z, boolean z2, int i, Rect rect, Rect rect2, Rect rect3, boolean z3, Rect rect4, boolean z4, Rect rect5, float f, boolean z5, List<Rect> list) {
        this.timestamp = j;
        this.zzbnu = z;
        this.zzbnv = z2;
        this.zzzk = i;
        this.zzbnw = rect;
        this.zzbnx = rect2;
        this.zzbny = rect3;
        this.zzbnz = z3;
        this.zzboa = rect4;
        this.zzbob = z4;
        this.zzboc = rect5;
        this.zzbod = f;
        this.zzbnr = z5;
        this.zzboe = list;
    }
}
