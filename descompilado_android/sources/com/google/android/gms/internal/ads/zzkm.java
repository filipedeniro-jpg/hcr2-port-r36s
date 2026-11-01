package com.google.android.gms.internal.ads;

import android.media.MediaCodec;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkm extends Exception {
    private final String mimeType;
    private final boolean zzaxn;
    private final String zzaxo;
    private final String zzaxp;

    public zzkm(zzgq zzgqVar, Throwable th, boolean z, int i) {
        String strValueOf = String.valueOf(zzgqVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 36);
        sb.append("Decoder init failed: [");
        sb.append(i);
        sb.append("], ");
        sb.append(strValueOf);
        super(sb.toString(), th);
        this.mimeType = zzgqVar.zzafb;
        this.zzaxn = false;
        this.zzaxo = null;
        String str = i < 0 ? "neg_" : "";
        int iAbs = Math.abs(i);
        StringBuilder sb2 = new StringBuilder(str.length() + 64);
        sb2.append("com.google.android.exoplayer.MediaCodecTrackRenderer_");
        sb2.append(str);
        sb2.append(iAbs);
        this.zzaxp = sb2.toString();
    }

    public zzkm(zzgq zzgqVar, Throwable th, boolean z, String str) {
        String strValueOf = String.valueOf(zzgqVar);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23 + String.valueOf(strValueOf).length());
        sb.append("Decoder init failed: ");
        sb.append(str);
        sb.append(", ");
        sb.append(strValueOf);
        super(sb.toString(), th);
        this.mimeType = zzgqVar.zzafb;
        this.zzaxn = false;
        this.zzaxo = str;
        String diagnosticInfo = null;
        if (zzoh.SDK_INT >= 21 && (th instanceof MediaCodec.CodecException)) {
            diagnosticInfo = ((MediaCodec.CodecException) th).getDiagnosticInfo();
        }
        this.zzaxp = diagnosticInfo;
    }
}
