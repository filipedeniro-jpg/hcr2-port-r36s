package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.tonyodev.fetch.FetchService;
import java.nio.ByteBuffer;
import net.hockeyapp.android.Strings;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzhe {
    private static final int[] zzagt = {1, 2, 3, 6};
    private static final int[] zzagu = {48000, 44100, 32000};
    private static final int[] zzagv = {24000, 22050, 16000};
    private static final int[] zzagw = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] zzagx = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, PsExtractor.AUDIO_STREAM, 224, 256, FetchService.ACTION_LOGGING, 384, 448, 512, 576, 640};
    private static final int[] zzagy = {69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static zzgq zza(zzoa zzoaVar, String str, String str2, zzil zzilVar) {
        int i = zzagu[(zzoaVar.readUnsignedByte() & PsExtractor.AUDIO_STREAM) >> 6];
        int unsignedByte = zzoaVar.readUnsignedByte();
        int i2 = zzagw[(unsignedByte & 56) >> 3];
        if ((unsignedByte & 4) != 0) {
            i2++;
        }
        return zzgq.zza(str, MimeTypes.AUDIO_AC3, null, -1, -1, i2, i, null, null, 0, str2);
    }

    public static int zzep() {
        return Strings.PAINT_INDICATOR_TOAST_ID;
    }

    public static zzgq zzb(zzoa zzoaVar, String str, String str2, zzil zzilVar) {
        zzoaVar.zzbe(2);
        int i = zzagu[(zzoaVar.readUnsignedByte() & PsExtractor.AUDIO_STREAM) >> 6];
        int unsignedByte = zzoaVar.readUnsignedByte();
        int i2 = zzagw[(unsignedByte & 14) >> 1];
        if ((unsignedByte & 1) != 0) {
            i2++;
        }
        return zzgq.zza(str, MimeTypes.AUDIO_E_AC3, null, -1, -1, i2, i, null, null, 0, str2);
    }

    public static int zzh(ByteBuffer byteBuffer) {
        return (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3 ? zzagt[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4] : 6) * 256;
    }
}
