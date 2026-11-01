package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzate {

    @VisibleForTesting
    private final String zzdqy;
    private final zzato zzdqz;

    @VisibleForTesting
    private long zzdqt = -1;

    @VisibleForTesting
    private long zzdqu = -1;

    @VisibleForTesting
    @GuardedBy("lock")
    private int zzdqv = -1;

    @VisibleForTesting
    int zzdqw = -1;

    @VisibleForTesting
    private long zzdqx = 0;
    private final Object lock = new Object();

    @VisibleForTesting
    @GuardedBy("lock")
    private int zzdra = 0;

    @VisibleForTesting
    @GuardedBy("lock")
    private int zzdrb = 0;

    public zzate(String str, zzato zzatoVar) {
        this.zzdqy = str;
        this.zzdqz = zzatoVar;
    }

    public final void zztr() {
        synchronized (this.lock) {
            this.zzdra++;
        }
    }

    public final void zztq() {
        synchronized (this.lock) {
            this.zzdrb++;
        }
    }

    public final void zza(zztp zztpVar, long j) {
        synchronized (this.lock) {
            long jZzuz = this.zzdqz.zzuz();
            long jCurrentTimeMillis = com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis();
            if (this.zzdqu == -1) {
                if (jCurrentTimeMillis - jZzuz > ((Long) zzuo.zzoj().zzd(zzyt.zzcki)).longValue()) {
                    this.zzdqw = -1;
                } else {
                    this.zzdqw = this.zzdqz.zzva();
                }
                this.zzdqu = j;
                this.zzdqt = this.zzdqu;
            } else {
                this.zzdqt = j;
            }
            if (zztpVar == null || zztpVar.extras == null || zztpVar.extras.getInt("gw", 2) != 1) {
                this.zzdqv++;
                this.zzdqw++;
                if (this.zzdqw == 0) {
                    this.zzdqx = 0L;
                    this.zzdqz.zzeu(jCurrentTimeMillis);
                } else {
                    this.zzdqx = jCurrentTimeMillis - this.zzdqz.zzvb();
                }
            }
        }
    }

    public final Bundle zzo(Context context, String str) {
        Bundle bundle;
        synchronized (this.lock) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzdqy);
            bundle.putLong("basets", this.zzdqu);
            bundle.putLong("currts", this.zzdqt);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzdqv);
            bundle.putInt("preqs_in_session", this.zzdqw);
            bundle.putLong("time_in_session", this.zzdqx);
            bundle.putInt("pclick", this.zzdra);
            bundle.putInt("pimp", this.zzdrb);
            bundle.putBoolean("support_transparent_background", zzal(context));
        }
        return bundle;
    }

    private static boolean zzal(Context context) {
        Context contextZzz = zzapf.zzz(context);
        int identifier = contextZzz.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            zzatm.zzet("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == contextZzz.getPackageManager().getActivityInfo(new ComponentName(contextZzz.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzatm.zzet("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            zzatm.zzeu("Fail to fetch AdActivity theme");
            zzatm.zzet("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }
}
