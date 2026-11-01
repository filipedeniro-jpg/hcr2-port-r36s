package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"InlinedApi"})
@TargetApi(16)
public final class zzkr {
    private static final SparseIntArray zzazf;
    private static final SparseIntArray zzazg;
    private static final Map<String, Integer> zzazh;
    private static final zzkk zzazc = zzkk.zzay("OMX.google.raw.decoder");
    private static final Pattern zzazd = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap<zza, List<zzkk>> zzaze = new HashMap<>();
    private static int zzazi = -1;

    public static zzkk zzgs() {
        return zzazc;
    }

    public static zzkk zzc(String str, boolean z) throws zzks {
        List<zzkk> listZzd = zzd(str, z);
        if (listZzd.isEmpty()) {
            return null;
        }
        return listZzd.get(0);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static final class zza {
        public final String mimeType;
        public final boolean zzaxk;

        public zza(String str, boolean z) {
            this.mimeType = str;
            this.zzaxk = z;
        }

        public final int hashCode() {
            String str = this.mimeType;
            return (((str == null ? 0 : str.hashCode()) + 31) * 31) + (this.zzaxk ? 1231 : 1237);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && obj.getClass() == zza.class) {
                zza zzaVar = (zza) obj;
                if (TextUtils.equals(this.mimeType, zzaVar.mimeType) && this.zzaxk == zzaVar.zzaxk) {
                    return true;
                }
            }
            return false;
        }
    }

    private static synchronized List<zzkk> zzd(String str, boolean z) throws zzks {
        zza zzaVar = new zza(str, z);
        List<zzkk> list = zzaze.get(zzaVar);
        if (list != null) {
            return list;
        }
        List<zzkk> listZza = zza(zzaVar, zzoh.SDK_INT >= 21 ? new zzkw(z) : new zzkt());
        if (z && listZza.isEmpty() && 21 <= zzoh.SDK_INT && zzoh.SDK_INT <= 23) {
            listZza = zza(zzaVar, new zzkt());
            if (!listZza.isEmpty()) {
                String str2 = listZza.get(0).name;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 63 + String.valueOf(str2).length());
                sb.append("MediaCodecList API didn't list secure decoder for: ");
                sb.append(str);
                sb.append(". Assuming: ");
                sb.append(str2);
                Log.w("MediaCodecUtil", sb.toString());
            }
        }
        List<zzkk> listUnmodifiableList = Collections.unmodifiableList(listZza);
        zzaze.put(zzaVar, listUnmodifiableList);
        return listUnmodifiableList;
    }

    public static int zzgt() throws zzks {
        int i;
        if (zzazi == -1) {
            int iMax = 0;
            zzkk zzkkVarZzc = zzc(MimeTypes.VIDEO_H264, false);
            if (zzkkVarZzc != null) {
                MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArrZzgl = zzkkVarZzc.zzgl();
                int length = codecProfileLevelArrZzgl.length;
                int iMax2 = 0;
                while (iMax < length) {
                    int i2 = codecProfileLevelArrZzgl[iMax].level;
                    if (i2 != 1 && i2 != 2) {
                        switch (i2) {
                            case 8:
                            case 16:
                            case 32:
                                i = 101376;
                                break;
                            case 64:
                                i = 202752;
                                break;
                            case 128:
                            case 256:
                                i = 414720;
                                break;
                            case 512:
                                i = 921600;
                                break;
                            case 1024:
                                i = 1310720;
                                break;
                            case 2048:
                            case 4096:
                                i = 2097152;
                                break;
                            case 8192:
                                i = 2228224;
                                break;
                            case 16384:
                                i = 5652480;
                                break;
                            case 32768:
                            case 65536:
                                i = 9437184;
                                break;
                            default:
                                i = -1;
                                break;
                        }
                    } else {
                        i = 25344;
                    }
                    iMax2 = Math.max(i, iMax2);
                    iMax++;
                }
                iMax = Math.max(iMax2, zzoh.SDK_INT >= 21 ? 345600 : 172800);
            }
            zzazi = iMax;
        }
        return zzazi;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:20:0x0040  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static Pair<Integer, Integer> zzbb(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("\\.");
        byte b = 0;
        String str2 = strArrSplit[0];
        int i = 2;
        switch (str2.hashCode()) {
            case 3006243:
                if (!str2.equals("avc1")) {
                    b = -1;
                } else {
                    b = 2;
                }
                break;
            case 3006244:
                if (!str2.equals("avc2")) {
                    b = -1;
                } else {
                    b = 3;
                }
                break;
            case 3199032:
                if (!str2.equals("hev1")) {
                    b = -1;
                }
                break;
            case 3214780:
                if (!str2.equals("hvc1")) {
                    b = -1;
                } else {
                    b = 1;
                }
                break;
            default:
                b = -1;
                break;
        }
        if (b != 0 && b != 1) {
            if (b == 2 || b == 3) {
                return zza(str, strArrSplit);
            }
            return null;
        }
        if (strArrSplit.length < 4) {
            String strValueOf = String.valueOf(str);
            Log.w("MediaCodecUtil", strValueOf.length() != 0 ? "Ignoring malformed HEVC codec string: ".concat(strValueOf) : new String("Ignoring malformed HEVC codec string: "));
            return null;
        }
        Matcher matcher = zzazd.matcher(strArrSplit[1]);
        if (!matcher.matches()) {
            String strValueOf2 = String.valueOf(str);
            Log.w("MediaCodecUtil", strValueOf2.length() != 0 ? "Ignoring malformed HEVC codec string: ".concat(strValueOf2) : new String("Ignoring malformed HEVC codec string: "));
            return null;
        }
        String strGroup = matcher.group(1);
        if ("1".equals(strGroup)) {
            i = 1;
        } else if (!"2".equals(strGroup)) {
            String strValueOf3 = String.valueOf(strGroup);
            Log.w("MediaCodecUtil", strValueOf3.length() != 0 ? "Unknown HEVC profile string: ".concat(strValueOf3) : new String("Unknown HEVC profile string: "));
            return null;
        }
        Integer num = zzazh.get(strArrSplit[3]);
        if (num == null) {
            String strValueOf4 = String.valueOf(matcher.group(1));
            Log.w("MediaCodecUtil", strValueOf4.length() != 0 ? "Unknown HEVC level string: ".concat(strValueOf4) : new String("Unknown HEVC level string: "));
            return null;
        }
        return new Pair<>(Integer.valueOf(i), num);
    }

    /* JADX WARN: Code duplicated, block: B:127:0x01fa  */
    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.google.android.gms.internal.ads.zzks.<init>(java.lang.Throwable, com.google.android.gms.internal.ads.zzkq):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:309)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:224)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:93)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    private static java.util.List<com.google.android.gms.internal.ads.zzkk> zza(com.google.android.gms.internal.ads.zzkr.zza r17, com.google.android.gms.internal.ads.zzku r18) throws com.google.android.gms.internal.ads.zzks {
        /*
            Method dump skipped, instruction units count: 696
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkr.zza(com.google.android.gms.internal.ads.zzkr$zza, com.google.android.gms.internal.ads.zzku):java.util.List");
    }

    private static Pair<Integer, Integer> zza(String str, String[] strArr) {
        Integer numValueOf;
        Integer numValueOf2;
        if (strArr.length < 2) {
            String strValueOf = String.valueOf(str);
            Log.w("MediaCodecUtil", strValueOf.length() != 0 ? "Ignoring malformed AVC codec string: ".concat(strValueOf) : new String("Ignoring malformed AVC codec string: "));
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                Integer numValueOf3 = Integer.valueOf(Integer.parseInt(strArr[1].substring(0, 2), 16));
                numValueOf2 = Integer.valueOf(Integer.parseInt(strArr[1].substring(4), 16));
                numValueOf = numValueOf3;
            } else if (strArr.length >= 3) {
                numValueOf = Integer.valueOf(Integer.parseInt(strArr[1]));
                numValueOf2 = Integer.valueOf(Integer.parseInt(strArr[2]));
            } else {
                String strValueOf2 = String.valueOf(str);
                Log.w("MediaCodecUtil", strValueOf2.length() != 0 ? "Ignoring malformed AVC codec string: ".concat(strValueOf2) : new String("Ignoring malformed AVC codec string: "));
                return null;
            }
            Integer numValueOf4 = Integer.valueOf(zzazf.get(numValueOf.intValue()));
            if (numValueOf4 == null) {
                String strValueOf3 = String.valueOf(numValueOf);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf3).length() + 21);
                sb.append("Unknown AVC profile: ");
                sb.append(strValueOf3);
                Log.w("MediaCodecUtil", sb.toString());
                return null;
            }
            Integer numValueOf5 = Integer.valueOf(zzazg.get(numValueOf2.intValue()));
            if (numValueOf5 == null) {
                String strValueOf4 = String.valueOf(numValueOf2);
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf4).length() + 19);
                sb2.append("Unknown AVC level: ");
                sb2.append(strValueOf4);
                Log.w("MediaCodecUtil", sb2.toString());
                return null;
            }
            return new Pair<>(numValueOf4, numValueOf5);
        } catch (NumberFormatException unused) {
            String strValueOf5 = String.valueOf(str);
            Log.w("MediaCodecUtil", strValueOf5.length() != 0 ? "Ignoring malformed AVC codec string: ".concat(strValueOf5) : new String("Ignoring malformed AVC codec string: "));
            return null;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        zzazf = sparseIntArray;
        sparseIntArray.put(66, 1);
        zzazf.put(77, 2);
        zzazf.put(88, 4);
        zzazf.put(100, 8);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        zzazg = sparseIntArray2;
        sparseIntArray2.put(10, 1);
        zzazg.put(11, 4);
        zzazg.put(12, 8);
        zzazg.put(13, 16);
        zzazg.put(20, 32);
        zzazg.put(21, 64);
        zzazg.put(22, 128);
        zzazg.put(30, 256);
        zzazg.put(31, 512);
        zzazg.put(32, 1024);
        zzazg.put(40, 2048);
        zzazg.put(41, 4096);
        zzazg.put(42, 8192);
        zzazg.put(50, 16384);
        zzazg.put(51, 32768);
        zzazg.put(52, 65536);
        HashMap map = new HashMap();
        zzazh = map;
        map.put("L30", 1);
        zzazh.put("L60", 4);
        zzazh.put("L63", 16);
        zzazh.put("L90", 64);
        zzazh.put("L93", 256);
        zzazh.put("L120", 1024);
        zzazh.put("L123", 4096);
        zzazh.put("L150", 16384);
        zzazh.put("L153", 65536);
        zzazh.put("L156", 262144);
        zzazh.put("L180", 1048576);
        zzazh.put("L183", 4194304);
        zzazh.put("L186", 16777216);
        zzazh.put("H30", 2);
        zzazh.put("H60", 8);
        zzazh.put("H63", 32);
        zzazh.put("H90", 128);
        zzazh.put("H93", 512);
        zzazh.put("H120", 2048);
        zzazh.put("H123", 8192);
        zzazh.put("H150", 32768);
        zzazh.put("H153", 131072);
        zzazh.put("H156", 524288);
        zzazh.put("H180", 2097152);
        zzazh.put("H183", 8388608);
        zzazh.put("H186", 33554432);
    }
}
