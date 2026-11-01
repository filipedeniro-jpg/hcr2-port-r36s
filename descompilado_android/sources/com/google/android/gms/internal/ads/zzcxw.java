package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcxw {
    private final Executor executor;
    private final Clock zzbmd;
    private final String zzbnh;
    private final String zzcdj;
    private final String zzdil;
    private final zzcvi zzfgh;
    private final zzcil zzfhh;
    private final zzaws zzgmj;
    private final Context zzlk;

    public zzcxw(Executor executor, zzaws zzawsVar, zzcil zzcilVar, zzawv zzawvVar, String str, String str2, Context context, @Nullable zzcvi zzcviVar, Clock clock) {
        this.executor = executor;
        this.zzgmj = zzawsVar;
        this.zzfhh = zzcilVar;
        this.zzbnh = zzawvVar.zzbnh;
        this.zzdil = str;
        this.zzcdj = str2;
        this.zzlk = context;
        this.zzfgh = zzcviVar;
        this.zzbmd = clock;
    }

    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, List<String> list) {
        zza(zzcvjVar, zzcvbVar, false, list);
    }

    public final void zza(zzcvj zzcvjVar, @Nullable zzcvb zzcvbVar, boolean z, List<String> list) {
        ArrayList arrayList = new ArrayList();
        String str = z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String strZzc = zzc(zzc(zzc(it.next(), "@gw_adlocid@", zzcvjVar.zzgjo.zzffn.zzgjt), "@gw_adnetrefresh@", str), "@gw_sdkver@", this.zzbnh);
            if (zzcvbVar != null) {
                strZzc = zzask.zzd(zzc(zzc(zzc(strZzc, "@gw_qdata@", zzcvbVar.zzdco), "@gw_adnetid@", zzcvbVar.zzaew), "@gw_allocid@", zzcvbVar.zzdbn), this.zzlk, zzcvbVar.zzdlf);
            }
            arrayList.add(zzc(zzc(zzc(strZzc, "@gw_adnetstatus@", this.zzfhh.zzaks()), "@gw_seqnum@", this.zzdil), "@gw_sessid@", this.zzcdj));
        }
        zzi(arrayList);
    }

    public final void zza(zzcvj zzcvjVar, zzcvb zzcvbVar, List<String> list, zzape zzapeVar) {
        long jCurrentTimeMillis = this.zzbmd.currentTimeMillis();
        try {
            String type = zzapeVar.getType();
            String string = Integer.toString(zzapeVar.getAmount());
            ArrayList arrayList = new ArrayList();
            zzcvi zzcviVar = this.zzfgh;
            String strZzgj = zzcviVar == null ? "" : zzgj(zzcviVar.zzdnn);
            zzcvi zzcviVar2 = this.zzfgh;
            String strZzgj2 = zzcviVar2 != null ? zzgj(zzcviVar2.zzdno) : "";
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(zzask.zzd(zzc(zzc(zzc(zzc(zzc(zzc(it.next(), "@gw_rwd_userid@", Uri.encode(strZzgj)), "@gw_rwd_custom_data@", Uri.encode(strZzgj2)), "@gw_tmstmp@", Long.toString(jCurrentTimeMillis)), "@gw_rwd_itm@", Uri.encode(type)), "@gw_rwd_amt@", string), "@gw_sdkver@", this.zzbnh), this.zzlk, zzcvbVar.zzdlf));
            }
            zzi(arrayList);
        } catch (RemoteException unused) {
        }
    }

    public final void zzi(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            zzei(it.next());
        }
    }

    public final void zzei(final String str) {
        this.executor.execute(new Runnable(this, str) { // from class: com.google.android.gms.internal.ads.zzcxz
            private final String zzcyl;
            private final zzcxw zzgmm;

            {
                this.zzgmm = this;
                this.zzcyl = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzgmm.zzgk(this.zzcyl);
            }
        });
    }

    private static String zzc(String str, String str2, @Nullable String str3) {
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        return str.replaceAll(str2, str3);
    }

    @Nullable
    private static String zzgj(@Nullable String str) {
        return (TextUtils.isEmpty(str) || !zzawi.isEnabled()) ? str : "fakeForAdDebugLog";
    }

    final /* synthetic */ void zzgk(String str) {
        this.zzgmj.zzei(str);
    }
}
