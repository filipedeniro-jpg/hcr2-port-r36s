package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
interface zzku {
    int getCodecCount();

    MediaCodecInfo getCodecInfoAt(int i);

    boolean zza(String str, MediaCodecInfo.CodecCapabilities codecCapabilities);

    boolean zzgu();
}
