package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzhf extends Exception {
    public zzhf(int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder(78);
        sb.append("Unhandled format: ");
        sb.append(i);
        sb.append(" Hz, ");
        sb.append(i2);
        sb.append(" channels in encoding ");
        sb.append(i3);
        super(sb.toString());
    }
}
