package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzhg {
    public static final ByteBuffer zzagz = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    void flush();

    boolean isActive();

    void reset();

    boolean zzb(int i, int i2, int i3) throws zzhf;

    boolean zzel();

    int zzeq();

    int zzer();

    void zzes();

    ByteBuffer zzet();

    void zzi(ByteBuffer byteBuffer);
}
