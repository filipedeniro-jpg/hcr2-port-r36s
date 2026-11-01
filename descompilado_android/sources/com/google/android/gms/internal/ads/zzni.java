package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzni extends IOException {
    private final int type;
    private final zznh zzbes;

    public zzni(String str, zznh zznhVar, int i) {
        super(str);
        this.zzbes = zznhVar;
        this.type = 1;
    }

    public zzni(IOException iOException, zznh zznhVar, int i) {
        super(iOException);
        this.zzbes = zznhVar;
        this.type = i;
    }

    public zzni(String str, IOException iOException, zznh zznhVar, int i) {
        super(str, iOException);
        this.zzbes = zznhVar;
        this.type = 1;
    }
}
