package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.SparseArray;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcez {
    private static final SparseArray<zzsh.zzj.zzb> zzfwe;
    private final zzcev zzfub;
    private final zzdcp<Bundle> zzfwa;
    private final TelephonyManager zzfwb;
    private final zzcep zzfwc;
    private zzso zzfwd;
    private final Context zzlk;

    zzcez(Context context, zzdcp<Bundle> zzdcpVar, zzcev zzcevVar, zzcep zzcepVar) {
        this.zzlk = context;
        this.zzfwa = zzdcpVar;
        this.zzfub = zzcevVar;
        this.zzfwc = zzcepVar;
        this.zzfwb = (TelephonyManager) context.getSystemService("phone");
    }

    private static zzso zzba(boolean z) {
        return z ? zzso.ENUM_TRUE : zzso.ENUM_FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zzsh.zzh zzj(Bundle bundle) {
        zzsh.zzh.zzb zzbVar;
        zzsh.zzh.zza zzaVarZzmy = zzsh.zzh.zzmy();
        int i = bundle.getInt("cnt", -2);
        int i2 = bundle.getInt("gnt", 0);
        if (i == -1) {
            this.zzfwd = zzso.ENUM_TRUE;
        } else {
            this.zzfwd = zzso.ENUM_FALSE;
            if (i == 0) {
                zzaVarZzmy.zza(zzsh.zzh.zzc.CELL);
            } else if (i == 1) {
                zzaVarZzmy.zza(zzsh.zzh.zzc.WIFI);
            } else {
                zzaVarZzmy.zza(zzsh.zzh.zzc.NETWORKTYPE_UNSPECIFIED);
            }
            switch (i2) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                case 16:
                    zzbVar = zzsh.zzh.zzb.TWO_G;
                    break;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                    zzbVar = zzsh.zzh.zzb.THREE_G;
                    break;
                case 13:
                    zzbVar = zzsh.zzh.zzb.LTE;
                    break;
                default:
                    zzbVar = zzsh.zzh.zzb.CELLULAR_NETWORK_TYPE_UNSPECIFIED;
                    break;
            }
            zzaVarZzmy.zza(zzbVar);
        }
        return (zzsh.zzh) ((zzdqd) zzaVarZzmy.zzazm());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzsh.zzj.zzb zzk(Bundle bundle) {
        return zzfwe.get(zzcvq.zza(zzcvq.zza(bundle, "device"), "network").getInt("active_network_state", -1), zzsh.zzj.zzb.UNSPECIFIED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x0050  */
    /* JADX WARN: Code duplicated, block: B:22:0x0062  */
    /* JADX WARN: Code duplicated, block: B:24:0x006a  */
    /* JADX WARN: Code duplicated, block: B:25:0x006c  */
    /* JADX WARN: Code duplicated, block: B:27:0x0074  */
    /* JADX WARN: Code duplicated, block: B:28:0x0076  */
    /* JADX WARN: Code duplicated, block: B:30:0x007e  */
    /* JADX WARN: Code duplicated, block: B:31:0x0080  */
    /* JADX WARN: Code duplicated, block: B:33:0x0088  */
    /* JADX WARN: Code duplicated, block: B:35:0x008b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x008d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x008f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:38:0x0091  */
    /* JADX WARN: Code duplicated, block: B:39:0x0094  */
    /* JADX WARN: Code duplicated, block: B:40:0x0097  */
    /* JADX WARN: Code duplicated, block: B:41:0x009a  */
    /* JADX WARN: Code duplicated, block: B:42:0x009d  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static ArrayList<zzsh.zzb.zza> zzl(Bundle bundle) {
        List<String> listEmptyList;
        List listAsList;
        ArrayList<zzsh.zzb.zza> arrayList;
        byte b;
        zzsh.zzb.zza zzaVar;
        Object obj = bundle.get("ad_types");
        if (obj instanceof List) {
            listAsList = (List) obj;
        } else {
            if (obj instanceof String[]) {
                listAsList = Arrays.asList((String[]) obj);
            } else {
                listEmptyList = Collections.emptyList();
            }
            arrayList = new ArrayList<>();
            for (String str : listEmptyList) {
                b = -1;
                switch (str.hashCode()) {
                    case -1396342996:
                        if (str.equals(ai.a.cW)) {
                            b = 0;
                        }
                        break;
                    case -1052618729:
                        if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                            b = 2;
                        }
                        break;
                    case -239580146:
                        if (str.equals(Constants.CONVERT_REWARDED)) {
                            b = 3;
                        }
                        break;
                    case 604727084:
                        if (str.equals(aa.e.bo)) {
                            b = 1;
                        }
                        break;
                }
                if (b != 0) {
                    zzaVar = zzsh.zzb.zza.BANNER;
                } else if (b != 1) {
                    zzaVar = zzsh.zzb.zza.INTERSTITIAL;
                } else if (b != 2) {
                    zzaVar = zzsh.zzb.zza.NATIVE_APP_INSTALL;
                } else if (b != 3) {
                    zzaVar = zzsh.zzb.zza.REWARD_BASED_VIDEO_AD;
                } else {
                    zzaVar = zzsh.zzb.zza.AD_FORMAT_TYPE_UNSPECIFIED;
                }
                arrayList.add(zzaVar);
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(listAsList.size());
        for (Object obj2 : listAsList) {
            if (obj2 instanceof String) {
                arrayList2.add((String) obj2);
            }
        }
        listEmptyList = Collections.unmodifiableList(arrayList2);
        arrayList = new ArrayList<>();
        while (r7.hasNext()) {
            b = -1;
            switch (str.hashCode()) {
                case -1396342996:
                    if (str.equals(ai.a.cW)) {
                        b = 0;
                    }
                    break;
                case -1052618729:
                    if (str.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                        b = 2;
                    }
                    break;
                case -239580146:
                    if (str.equals(Constants.CONVERT_REWARDED)) {
                        b = 3;
                    }
                    break;
                case 604727084:
                    if (str.equals(aa.e.bo)) {
                        b = 1;
                    }
                    break;
            }
            if (b != 0) {
                zzaVar = zzsh.zzb.zza.BANNER;
            } else if (b != 1) {
                zzaVar = zzsh.zzb.zza.INTERSTITIAL;
            } else if (b != 2) {
                zzaVar = zzsh.zzb.zza.NATIVE_APP_INSTALL;
            } else if (b != 3) {
                zzaVar = zzsh.zzb.zza.REWARD_BASED_VIDEO_AD;
            } else {
                zzaVar = zzsh.zzb.zza.AD_FORMAT_TYPE_UNSPECIFIED;
            }
            arrayList.add(zzaVar);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] zza(boolean z, ArrayList<zzsh.zzb.zza> arrayList, zzsh.zzh zzhVar, zzsh.zzj.zzb zzbVar) {
        return ((zzsh.zzj.zza) ((zzdqd) zzsh.zzj.zza.zznc().zzc(arrayList).zzh(zzba(com.google.android.gms.ads.internal.zzp.zzka().zzb(this.zzlk.getContentResolver()) != 0)).zzi(com.google.android.gms.ads.internal.zzp.zzka().zza(this.zzlk, this.zzfwb)).zzen(this.zzfub.zzakj()).zzeo(this.zzfub.zzakl()).zzca(this.zzfub.getResponseCode()).zzb(zzbVar).zzb(zzhVar).zzj(this.zzfwd).zzf(zzba(z)).zzem(com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis()).zzg(zzba(com.google.android.gms.ads.internal.zzp.zzka().zza(this.zzlk.getContentResolver()) != 0)).zzazm())).toByteArray();
    }

    public final void zzbb(boolean z) {
        zzdcf.zza(this.zzfwa, new zzcfc(this, z), zzawx.zzdwb);
    }

    static {
        SparseArray<zzsh.zzj.zzb> sparseArray = new SparseArray<>();
        zzfwe = sparseArray;
        sparseArray.put(NetworkInfo.DetailedState.CONNECTED.ordinal(), zzsh.zzj.zzb.CONNECTED);
        zzfwe.put(NetworkInfo.DetailedState.AUTHENTICATING.ordinal(), zzsh.zzj.zzb.CONNECTING);
        zzfwe.put(NetworkInfo.DetailedState.CONNECTING.ordinal(), zzsh.zzj.zzb.CONNECTING);
        zzfwe.put(NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal(), zzsh.zzj.zzb.CONNECTING);
        zzfwe.put(NetworkInfo.DetailedState.DISCONNECTING.ordinal(), zzsh.zzj.zzb.DISCONNECTING);
        zzfwe.put(NetworkInfo.DetailedState.BLOCKED.ordinal(), zzsh.zzj.zzb.DISCONNECTED);
        zzfwe.put(NetworkInfo.DetailedState.DISCONNECTED.ordinal(), zzsh.zzj.zzb.DISCONNECTED);
        zzfwe.put(NetworkInfo.DetailedState.FAILED.ordinal(), zzsh.zzj.zzb.DISCONNECTED);
        zzfwe.put(NetworkInfo.DetailedState.IDLE.ordinal(), zzsh.zzj.zzb.DISCONNECTED);
        zzfwe.put(NetworkInfo.DetailedState.SCANNING.ordinal(), zzsh.zzj.zzb.DISCONNECTED);
        zzfwe.put(NetworkInfo.DetailedState.SUSPENDED.ordinal(), zzsh.zzj.zzb.SUSPENDED);
        if (Build.VERSION.SDK_INT >= 17) {
            zzfwe.put(NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal(), zzsh.zzj.zzb.CONNECTING);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            zzfwe.put(NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal(), zzsh.zzj.zzb.CONNECTING);
        }
    }
}
