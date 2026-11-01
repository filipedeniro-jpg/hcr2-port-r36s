package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdtw {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Object zzhqq = new Object();

    public static void zza(zzdts zzdtsVar, zzdts zzdtsVar2) {
        if (zzdtsVar.zzhqk != null) {
            zzdtsVar2.zzhqk = (zzdtu) zzdtsVar.zzhqk.clone();
        }
    }
}
