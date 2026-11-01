package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public final class zzkk {
    private final String mimeType;
    public final String name;
    public final boolean zzajn;
    public final boolean zzaxj;
    public final boolean zzaxk;
    private final MediaCodecInfo.CodecCapabilities zzaxl;

    public static zzkk zzay(String str) {
        return new zzkk(str, null, null, false, false);
    }

    public static zzkk zza(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        return new zzkk(str, str2, codecCapabilities, z, z2);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002a  */
    /* JADX WARN: Code duplicated, block: B:24:0x0044  */
    /* JADX WARN: Code duplicated, block: B:36:0x005d  */
    private zzkk(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        this.name = (String) zznt.checkNotNull(str);
        this.mimeType = str2;
        this.zzaxl = codecCapabilities;
        boolean z5 = true;
        if (z || codecCapabilities == null) {
            z3 = false;
        } else {
            if (zzoh.SDK_INT >= 19 && codecCapabilities.isFeatureSupported("adaptive-playback")) {
                z3 = true;
            } else {
                z3 = false;
            }
        }
        this.zzaxj = z3;
        if (codecCapabilities == null) {
            z4 = false;
        } else {
            if (zzoh.SDK_INT >= 21 && codecCapabilities.isFeatureSupported("tunneled-playback")) {
                z4 = true;
            } else {
                z4 = false;
            }
        }
        this.zzajn = z4;
        if (!z2) {
            if (codecCapabilities == null) {
                z5 = false;
            } else {
                if (!(zzoh.SDK_INT >= 21 && codecCapabilities.isFeatureSupported("secure-playback"))) {
                    z5 = false;
                }
            }
        }
        this.zzaxk = z5;
    }

    public final MediaCodecInfo.CodecProfileLevel[] zzgl() {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzaxl;
        if (codecCapabilities == null || codecCapabilities.profileLevels == null) {
            return new MediaCodecInfo.CodecProfileLevel[0];
        }
        return this.zzaxl.profileLevels;
    }

    /* JADX WARN: Code duplicated, block: B:59:0x00c3  */
    public final boolean zzaz(String str) {
        String str2;
        if (str == null || this.mimeType == null) {
            return true;
        }
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.startsWith("avc1") || strTrim.startsWith("avc3")) {
                str2 = MimeTypes.VIDEO_H264;
            } else if (strTrim.startsWith("hev1") || strTrim.startsWith("hvc1")) {
                str2 = MimeTypes.VIDEO_H265;
            } else if (strTrim.startsWith("vp9")) {
                str2 = MimeTypes.VIDEO_VP9;
            } else if (strTrim.startsWith("vp8")) {
                str2 = MimeTypes.VIDEO_VP8;
            } else if (strTrim.startsWith("mp4a")) {
                str2 = MimeTypes.AUDIO_AAC;
            } else if (strTrim.startsWith("ac-3") || strTrim.startsWith("dac3")) {
                str2 = MimeTypes.AUDIO_AC3;
            } else if (strTrim.startsWith("ec-3") || strTrim.startsWith("dec3")) {
                str2 = MimeTypes.AUDIO_E_AC3;
            } else if (strTrim.startsWith("dtsc") || strTrim.startsWith("dtse")) {
                str2 = MimeTypes.AUDIO_DTS;
            } else if (strTrim.startsWith("dtsh") || strTrim.startsWith("dtsl")) {
                str2 = MimeTypes.AUDIO_DTS_HD;
            } else if (strTrim.startsWith("opus")) {
                str2 = MimeTypes.AUDIO_OPUS;
            } else if (strTrim.startsWith("vorbis")) {
                str2 = MimeTypes.AUDIO_VORBIS;
            } else {
                str2 = null;
            }
        } else {
            str2 = null;
        }
        if (str2 == null) {
            return true;
        }
        if (!this.mimeType.equals(str2)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(str2).length());
            sb.append("codec.mime ");
            sb.append(str);
            sb.append(", ");
            sb.append(str2);
            zzba(sb.toString());
            return false;
        }
        Pair<Integer, Integer> pairZzbb = zzkr.zzbb(str);
        if (pairZzbb == null) {
            return true;
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : zzgl()) {
            if (codecProfileLevel.profile == ((Integer) pairZzbb.first).intValue() && codecProfileLevel.level >= ((Integer) pairZzbb.second).intValue()) {
                return true;
            }
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 22 + String.valueOf(str2).length());
        sb2.append("codec.profileLevel, ");
        sb2.append(str);
        sb2.append(", ");
        sb2.append(str2);
        zzba(sb2.toString());
        return false;
    }

    @TargetApi(21)
    public final boolean zza(int i, int i2, double d) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzaxl;
        if (codecCapabilities == null) {
            zzba("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzba("sizeAndRate.vCaps");
            return false;
        }
        if (zza(videoCapabilities, i, i2, d)) {
            return true;
        }
        if (i >= i2 || !zza(videoCapabilities, i2, i, d)) {
            StringBuilder sb = new StringBuilder(69);
            sb.append("sizeAndRate.support, ");
            sb.append(i);
            sb.append("x");
            sb.append(i2);
            sb.append("x");
            sb.append(d);
            zzba(sb.toString());
            return false;
        }
        StringBuilder sb2 = new StringBuilder(69);
        sb2.append("sizeAndRate.rotated, ");
        sb2.append(i);
        sb2.append("x");
        sb2.append(i2);
        sb2.append("x");
        sb2.append(d);
        String string = sb2.toString();
        String str = this.name;
        String str2 = this.mimeType;
        String str3 = zzoh.zzbgs;
        StringBuilder sb3 = new StringBuilder(String.valueOf(string).length() + 25 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb3.append("AssumedSupport [");
        sb3.append(string);
        sb3.append("] [");
        sb3.append(str);
        sb3.append(", ");
        sb3.append(str2);
        sb3.append("] [");
        sb3.append(str3);
        sb3.append(Constants.RequestParameters.RIGHT_BRACKETS);
        Log.d(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, sb3.toString());
        return true;
    }

    @TargetApi(21)
    public final Point zzc(int i, int i2) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzaxl;
        if (codecCapabilities == null) {
            zzba("align.caps");
            return null;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzba("align.vCaps");
            return null;
        }
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(zzoh.zze(i, widthAlignment) * widthAlignment, zzoh.zze(i2, heightAlignment) * heightAlignment);
    }

    @TargetApi(21)
    public final boolean zzap(int i) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzaxl;
        if (codecCapabilities == null) {
            zzba("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            zzba("sampleRate.aCaps");
            return false;
        }
        if (audioCapabilities.isSampleRateSupported(i)) {
            return true;
        }
        StringBuilder sb = new StringBuilder(31);
        sb.append("sampleRate.support, ");
        sb.append(i);
        zzba(sb.toString());
        return false;
    }

    @TargetApi(21)
    public final boolean zzaq(int i) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzaxl;
        if (codecCapabilities == null) {
            zzba("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            zzba("channelCount.aCaps");
            return false;
        }
        if (audioCapabilities.getMaxInputChannelCount() >= i) {
            return true;
        }
        StringBuilder sb = new StringBuilder(33);
        sb.append("channelCount.support, ");
        sb.append(i);
        zzba(sb.toString());
        return false;
    }

    private final void zzba(String str) {
        String str2 = this.name;
        String str3 = this.mimeType;
        String str4 = zzoh.zzbgs;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
        sb.append("NoSupport [");
        sb.append(str);
        sb.append("] [");
        sb.append(str2);
        sb.append(", ");
        sb.append(str3);
        sb.append("] [");
        sb.append(str4);
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        Log.d(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, sb.toString());
    }

    @TargetApi(21)
    private static boolean zza(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
        if (d == -1.0d || d <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return videoCapabilities.isSizeSupported(i, i2);
        }
        return videoCapabilities.areSizeAndRateSupported(i, i2, d);
    }
}
